$(window).ready(function() {

    $("#btn-signup").click(function (){
        let loc = document.querySelector("#web-signup").getAttribute("data-contextPath");
        return window.location.replace(loc);
    });

    $("#btn-login").click(function () {
        let email = $("#email").val();
        let password = $("#password").val();
        let platformType = 'NONSOCIAL';
        let data;

        if(!email || !password){
            bootbox.alert({
                size: "small",
                title: "알림",
                message: `입력되지 않은 정보가 있습니다.<br>(이메일, 패스워드)`,
                callback: function () {}
            });
        }
        else if($('#recaptcha').val() === '0'){
            // recaptcha 대상이 아닌 경우
            data = JSON.stringify({'email': email, 'password': password, 'platformType': platformType});
        }
        else{
            // recaptcha 대상인 경우
            let recaptcha = $("#g-recaptcha-response").val();
            if(!recaptcha || $('#btn-login').hasClass('disabled-btn')){
                return bootbox.alert({
                    size: "small",
                    title: "알림",
                    message: `recaptcha 인증 후 진행이 가능합니다.`,
                    callback: function () {}
                });
            }
            data = JSON.stringify({'email': email, 'password': password, 'platformType': platformType, 'recaptcha': recaptcha});
        }

        $.ajax({
            url : "/signin",
            method: "post",
            contentType : "application/json; charset=utf-8",
            data: data,
            dataType : "json",
            success : function(response){
                console.log(response);
                if(response.code !== 'A0000'){
                    bootbox.alert({
                        size: "small",
                        title: "알림",
                        message: `${response.reason}`,
                        callback: function () {
                            let loc = document.querySelector("#web-signin").getAttribute("data-contextPath");
                            return window.location.replace(loc);
                        }
                    });
                }
                else{
                    bootbox.alert({
                        size: "small",
                        title: "알림",
                        message: `안녕하세요. ${response.data.name}님<br>로그인에 성공하여 페이지를 이동합니다.`,
                        callback: function () {
                            let loc = document.querySelector("#web-index").getAttribute("data-contextPath");
                            return window.location.replace(loc);
                        }
                    });
                }
            }
        });
    });


    $("#btn-kakao-login").click(function () {
        $.ajax({
            url : "/signin/client-info?platformType=KAKAO",
            method: "get",
            contentType : "application/json; charset=utf-8",
            dataType : "json",
            success : function(response){
                console.log(response);
                if(response.code !== 'A0000'){
                    bootbox.alert({
                        size: "middle",
                        title: "알림",
                        message: `죄송합니다. 일시적인 오류로 인해서 처리하지 못했습니다.<br>잠시 뒤에 시도해주세요.`,
                        callback: function () {
                            window.location.replace('http://localhost:8000')
                        }
                    });
                }
                else{
                    window.location.replace(`${response.data.total}`);
                }
            }
        });
    });


});

<!-- cover html 주석처리-->
// window.addEventListener("load", () => {
//     "use strict";
//     let state = 2;
//
//     function coverPosition(pos) {
//         document.getElementById("cover").style.bottom = pos;
//     }
//
//     document.getElementById("handle").addEventListener("click", () => {
//         if (state === 1) {
//             state = 2;
//             coverPosition("80%");
//         } else if (state === 2) {
//             state = 1;
//             coverPosition("20%");
//         }
//     });
//
// });