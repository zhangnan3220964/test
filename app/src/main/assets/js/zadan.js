$(function() {
    $("#switch").click(function() {
        if ($("#open").css("display") == 'none') {
            $("#close").css("display", 'none');
            $("#open").css("display", "block");

        } else {
            $("#close").css("display", 'block');
            $("#open").css("display", "none");
        }
    });
    $('#time div').click(function() {
        let number = $(this).attr('number')
        $('#egg_body').css('animation', 'null')
        $('#egg_body').css('display', 'none')
        $('#time').css('display', 'none')
        $('#egg_half').css('display', 'block')
        $('#light').css('display', 'block')
        $('#light').css('animation', 'rotate 5s linear infinite')
        $('#gift').css('display', 'block')
        $('#result').css('display', 'block')
        $('#hammer').css('animation', 'null')
    })
    $("#cha").click(function() {
        $("#egg_body").css("display", "block");
        $("#time").css("display", "flex");
        $("#egg_half").css("display", 'none');
        $("#light").css("display", 'none');
        $("#gift").css("display", 'none');
        $("#result").css("display", 'none');
        $('#egg_body').css('animation', 'move 2s 1s linear infinite normal')
        $('#hammer').css('animation', 'move2 2s ease-in-out infinite reverse')
    });

    /*$("#open").click(function () {
        $("#close").css("display",'block');
        $("#open").css("display","none");
    })
    $("#close").click(function () {
        $("#close").css("display",'none');
        $("#open").css("display","block");
    })*/
})