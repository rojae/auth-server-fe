$("#btn-signup-pre-step2").click(function (){

    let email = $("#email").val();
    let password = $("#password").val();
    let passwordConfirm = $("#passwordConfirm").val();

    if(password !== passwordConfirm){
        return bootbox.alert({
            size: "small",
            title: "알림",
            message: `패스워드가 서로 달라요. 확인 해주세요`
        });
    }

    let dataJson = JSON.stringify({'email': email, 'password': password});

    $.ajax({
        url : "/api/v1/signup/basic-info/password",
        method: "post",
        beforeSend: function(request) {
            request.setRequestHeader("signup_step_uuid", getCookie('signup_step_uuid'));
        },
        contentType : "application/json; charset=utf-8",
        dataType : "json",
        data : dataJson,
        success : function(response){
            console.log(response);

            if(response.code === 'S0000'){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `이메일과 비밀번호 입력이 완료되었어요`,
                    callback: function () {
                        return bootbox.alert({
                            size: "middle",
                            title: "알림",
                            message: `아직 개발 중인 페이지이예요 😅<br/>로그인 페이지로 이동할게요`,
                            callback: function () {
                                let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
                                window.location.replace(loc);
                            }
                        });
                    }
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
            else if(response.code === 'S0002') {
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `데이터가 조작이 되었어요<br/>다시 진행해주세요`,
                    callback: function () {
                        window.location.replace("/home");
                    }
                });
            }
            else if(response.code === 'S0003') {
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: response.reason
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
