$("#btn-signup-pre-step1").click(function (){

    let email = $("#email").val();
    let mailType = "SIGNUP";
    let dataJson = JSON.stringify({'email': email, 'mailType': mailType});

    $.ajax({
        url : "/api/v1/mail/send/signupForAuth",
        method: "post",
        contentType : "application/json; charset=utf-8",
        dataType : "json",
        data : dataJson,
        success : function(response){
            console.log(response);
            if(response.code !== 'M0000'){
                exceptionRedirect();
            }
            else if(response.code === 'M0000'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이메일을 발송했어요<br/>수신한 인증코드를 입력해주세요`,
                    callback: function () {
                        $("#emailForm").css('display', 'none');
                        $("#email").attr("disabled",true);
                        $("#secretForm").css('display', 'block');
                        $("#btn-signup-step1").css('display', 'block');
                        $("#btn-signup-pre-step1").text('인증하기').removeAttr('onclick').css('display', 'none');
                    }
                });
            }
            else if(response.code === 'M0001'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이메일 발송에 실패했어요<br/>다시 한번 시도해주세요`,
                });
            }
        }
    });
});

$("#btn-signup-step1").click(function (){

    let secret = $("#secret").val();
    let email = $("#email").val();
    let dataJson = JSON.stringify({'secret': secret, 'email': email});

    $.ajax({
        url : "/api/v1/mail/verify/signupForAuth",
        method: "post",
        contentType : "application/json; charset=utf-8",
        dataType : "json",
        data : dataJson,
        success : function(response){
            console.log(response);
            if(response.code !== 'M0000' && response.code !== 'M0002'){
                exceptionRedirect();
            }
            else if(response.code === 'M0000'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `짝짝짝! &#127881;<br/>인증에 성공했어요`,
                    callback: function () {
                        alert('개발 중인 영역으로 로그인 페이지로 이동할게요');
                        let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
                        window.location.replace(loc);
                    }
                });
            }
            else if(response.code === 'M0002') {
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `인증코드가 잘못되었어요.<br/>다시 확인해주세요`,
                });
            }
        }
    });
});

function exceptionRedirect(){}
    bootbox.alert({
        size: "middle",
        title: "알림",
        message: `죄송합니다. 일시적인 오류로 인해서 처리하지 못했습니다.<br>잠시 뒤에 시도해주세요.`,
        callback: function () {
            let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
            window.location.replace(loc);
        }
});

