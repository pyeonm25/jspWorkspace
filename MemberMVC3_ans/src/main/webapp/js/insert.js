const checkBtn = document.querySelector("#btn-check"); 
const id = "test"; 
const isValid = false;
document.querySelector("form").addEventListener("submit", event =>{
	event.preventDefault(); // 기본 이벤트 막기
});

let data ="";
checkBtn.addEventListener("click", ()=>{

fetch('/MemberMVC3_ansdd1/validateAjax.do',
	{
	method: "post",
	headers: { 
		"Content-type" : "application/x-www-form-urlencoded; charset=UTF-8"
	},
	body :"id=" + id
}).then(response =>{
	
   console.log(response);
   console.log(response.text());
   
   data = response.text();
	
})
.catch(error => console.log(error))
	
	
})

function validCheck(form){
	console.log(form.id)
	console.log(form.id.value)
	console.log(form.pass.value)
	console.log(form.age.value)
	
	if(!form.phone.value.match(/010-\d{3,4}-\d{4}/)){
		alert("전화번호 형식이 다릅니다");
		return;	
	}
	
	if(isValid){
		form.submit(); // 전송 버튼을 함수로 누르는 것! 
	}
	
}