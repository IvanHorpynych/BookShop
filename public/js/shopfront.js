function getData() {

    $.ajax({
        url: 'http://localhost/list',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function(request) {
            request.setRequestHeader("token", getCookie("Auth-Token"));
        },
        success: function (data) {
            $.each(data, function(index, element) {
            if(index === "allowedBooks") {
            const table = document.getElementById("books");
                for(i = 0; i < element.length; i++){
                    let row = table.insertRow(i + 1);
                    row.insertCell(0).innerHTML = element[i].id;
                    row.insertCell(1).innerHTML = element[i].name;
                    row.insertCell(2).innerHTML = element[i].price;
                    row.insertCell(3).innerHTML = element[i].timesBought;
                }
            } else if(index === "currentUser"){
                const table = document.getElementById("user");
                    let row = table.insertRow(1);
                    row.insertCell(0).innerHTML = element.name;
                    row.insertCell(1).innerHTML = element.earnedMoney;
            }
            });
        }
    })
}

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}