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
            if(response.code === 'M0000'){
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
            else if(response.code === 'A3001'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이미 가입된 이메일 주소입니다<br/>다른 이메일을 사용해주세요`,
                });
            }
            else if(response.code === 'M0001'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이메일 발송에 실패했어요<br/>다시 한번 시도해주세요`,
                });
            }
            else{
                exceptionRedirect();
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
        beforeSend: function(request) {
            request.setRequestHeader("signup_step_uuid", getCookie('signup_step_uuid'));
        },
        contentType : "application/json; charset=utf-8",
        dataType : "json",
        data : dataJson,
        success : function(response){
            console.log(response);

            if(response.code === 'M0000'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `짝짝짝! 🎉<br/>인증에 성공했어요`,
                    callback: function () {
                        window.location.replace("/signup/step2");
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
            else if(response.code === 'S0001') {
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `잘못된 경로이거나 만료된 세션입니다<br/>다시 진행해주세요`,
                    callback: function () {
                        window.location.replace("/home");
                    }
                });
            }
            else{
                exceptionRedirect();
            }
        }
    });
});

function exceptionRedirect() {
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

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

