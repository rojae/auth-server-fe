$("#btn-signup-step1").click(function (){

    let email = $("#email").val();
    let mailType = "SIGNUP";

    let dataJson = JSON.stringify({'email': email, 'mailType': mailType});

    $.ajax({
        url : "https://smtp.rojae.kr/api/v1/mail/send/signupForAuth",
        method: "post",
        contentType : "application/json; charset=utf-8",
        dataType : "json",
        data : dataJson,
        success : function(response){
            console.log(response);
            if(response.code !== 'M0000'){
                bootbox.alert({
                    size: "middle",
                    title: "알림",
                    message: `죄송합니다. 일시적인 오류로 인해서 처리하지 못했습니다.<br>잠시 뒤에 시도해주세요.`,
                    callback: function () {
                        let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
                        window.location.replace(loc);
                    }
                });
            }
            else{
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이메일을 발송했어요<br/>수신한 인증코드를 입력해주세요`,
                    callback: function () {
                        alert('개발 중인 영역으로 로그인 페이지로 이동합니다')
                        let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
                        window.location.replace(loc);
                    }
                });
            }
        }
    });
});