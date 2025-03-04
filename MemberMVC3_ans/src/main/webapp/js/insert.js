const checkBtn = document.querySelector("#btn-check"); 
const id = "test"; 
checkBtn.addEventListener("click", ()=>{
///	const ctx = window.location.pathname;	
//	alert("ctx=" + ctx); // 

// fetch(url , {})

fetch('/MemberMVC3_ans//validateAjax.do',
	{
	method: "post",
	headers: { 
		"Content-type" : "application/x-www-form-urlencoded; charset=UTF-8"
	},
	body :"id=" + id
}).then(response =>{
	
   console.log(response);
   console.log(response.text());
	
})
.catch(error => console.log(error))
	
	
})