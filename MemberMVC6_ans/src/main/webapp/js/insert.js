let check = 0;
document.querySelector('form').addEventListener("submit", event => {
  event.preventDefault();
})
function validCheck(form) {


  if (!form.id.value.trim()) {
    alert("아이디를 입력해주세요");
    form.id.focus();
    return false;
  }
  if (!form.pass.value.trim()) {
    alert("패스워드를 입력해주세요");
    form.id.focus();
    return false;
  }
  if (!form.name.value.trim()) {
    alert("이름를 입력해주세요");
    form.id.focus();
    return false;
  }

  if (!form.age.value.trim()) {
    alert("나이를 입력해주세요");
    form.age.focus();
    return false;
  } else {
    if (Number(form.age.value.trim()) < 10 || Number(form.age.value.trim()) > 99) {
      alert("나이 값(10-99)을 정확하게 입력하세요");
      form.age.focus();
      return false;
    }
  }

  /*
  @ 앞에는 영어소문자,숫자 . _ % + - 만 허용
  @ 골뱅이 필수.
  @ 뒤 . 앞에는 영어소문자,숫자 . - 만 허용
  . 점 필수
  . 뒤에는 영어소문자 2자리 이상
  */

  if (!form.email.value.match(/[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/)) {
    alert("이메일 형식이 다릅니다");
    form.email.value = "test@test.com";
    form.email.focus();
    return false;
  }

  if (!form.phone.value.match(/010-\d{3,4}-\d{4}/)) {
    alert("전화번호 형식이 다릅니다");
    form.phone.value = "010-1234-1234";
    form.phone.focus();
    return false;
  }

  console.log("check = " + check);
  if (check == 0) {
    alert('id 중복체크 해주세요');
    return false;
  } else if (check == -1) {
    alert('id 중복체크 다시하세요');
    return false;
  }else if(check == 1){
  		form.submit();
  }

}


document.querySelector("#checkId").addEventListener("click", () => {
  let idInput = document.querySelector("#id");
  let id = idInput.value.trim();

  if (id.length === 0) {
    alert("id 값을 입력해주세요");
    idInput.focus();
    idInput.style.border = "";
    return;
  }

  fetch("vaildIdAjax.do", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
    },
    body: "id=" + id,
  })
    .then(response => response?.text())
    .then(getResult)
    .catch(error => console.log("error:" + error));
});

function getResult(data) {
  if (data === "valid") {
    alert("이 아이디는 사용가능 합니다  ");
    document.querySelector("#pass").focus();
    document.querySelector("#id").style.border = "3px blue solid";
    check = 1;
  } else if (data === "notValid") {
    alert("이 아이디는 사용 불가능 합니다");
    document.querySelector("#id").value = "";
    document.querySelector("#id").focus();
    document.querySelector("#id").style.border = "3px red solid";
    check = -1;
  }
}

document.querySelector("#id").addEventListener("keyup", (e) => {
  if (e.keyCode === 8) {
    check = 0;
  }
  document.querySelector("#id").style.border = "";
});