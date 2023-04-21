/**
 * Join Form에서 검증
 */

 $("#userName").blur(idDuplicateCheck);
 
 function idDuplicateCheck(){
	 console.log("ID 중복체크 실행")
	 
	 $.ajax({
		 type:"GET",
		 url:"./idDuplicateCheck",
		 data:{
			 userName:$('#userName').val()			
		 },
		 success:function(result){
			 if(result){
				 console.log('사용이 가능한 ID입니다.')
			 } else {
				 console.log('중복 된 ID입니다.')
			 }
		 },
		 error:function(){
			 console.log('error')
		 }
	 })
 }
 
 $("#password").addEventListener