function login() {
    var url = 'http://localhost/login';
    var userData = {
        email: document.getElementById('login-email').value,
        password: document.getElementById('login-password').value
    };

    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(userData),
        success: function(data, textStatus, request){
            document.getElementById('error-message').textContent= '';
            token = data["token"];
            document.cookie = "Auth-Token=" + token;
            if (token !== null) {
                window.location = '/shopfront.html';
            }
        },
        error: function (request, textStatus, errorThrown) {
            document.getElementById('error-message').textContent= request.getResponseHeader('error-message');
        }
    });

}