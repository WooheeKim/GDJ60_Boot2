/**
 * board Form 유효성 검사
 */

 const submitButton=document.getElementById("submitButton");
 
 submitButton.addEventListener("click", function(){
// 	 console('submit button click');
	 
// 	 let chkTitle = document.getElementById('title').value;
// 	 let chkWriter = document.getElementById('writer').value;
	
// 	 if(chkTitle!='' && chkWriter!=''){
// 		let frm = document.getElementById('contactForm');
// 		frm.submit();
// 	 }else{
// 		alert('title, writer는 필수 입력값입니다.');
// 	 }
	 
	 //jQuery
	let title = $('#title').val();
	let writer = $('#writer').val();
	let form = $('#contactForm');
	 
	 if(title==''){
		alert('title check');
		return false;
	}else if(writer==''){
		alert('writer check');
		return false;
	}else{
		form.submit();
	}
 });
