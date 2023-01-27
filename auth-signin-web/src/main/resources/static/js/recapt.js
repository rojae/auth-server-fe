$(window).ready(function(){
    grecaptcha.ready(function() {
        grecaptcha.render('g-recaptcha', {
            'sitekey' : '6Le6oxYkAAAAANge_rN0fpSQ-t1ycbOmKvmZITur',
            'callback' : verifyCallback,
            'expired-callback' : expiredCallback,
        });
    });

    function passRecaptcha(){
        $("#btn-login").removeClass("disabled-btn");
        $("#btn-login").attr("disabled", false);
    }

    function failRecaptcha(){
        $("#btn-login").addClass("disabled-btn");
        $("#btn-login").attr("disabled", true);
        // grecaptcha.reset();
    }

    function verifyCallback(){
        bootbox.alert({
            size: "small",
            title: "알림",
            message: `인증에 성공했습니다.`,
            callback: function () {
                passRecaptcha();
            }
        });
    }

    function expiredCallback(){
        bootbox.alert({
            size: "small",
            title: "알림",
            message: `인증 시간이 만료되었습니다. 다시 시도해주세요.`,
            callback: function () {
                failRecaptcha();
            }
        });
    }

    // $("#btn-recaptcha").click(function(){
    //     $.ajax({
    //         url : "/signin/recaptcha",
    //         method: "post",
    //         contentType : "application/json; charset=utf-8",
    //         data: $("#g-recaptcha-response").val(),
    //         dataType : "json",
    //         success : function(data){
    //             console.log(data);
    //         }
    //     });
    // });

    failRecaptcha();
    $('#recaptcha').val('1');
});

