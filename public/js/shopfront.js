var d = document, itemBox, basketCont, user;
var corsurl = "http://localhost";
function setData() {

    $.ajax({
        url: corsurl+'/list',
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        beforeSend: function (request) {
            request.setRequestHeader("token", getCookie("Auth-Token"));
        },
        success: function (data) {
            appendData(data);
            loadEventListeners();
            openBasket(this);
        }
    })
}

function addEvent(elem, type, handler) {
    if (elem.addEventListener) {
        elem.addEventListener(type, handler, false);
    } else {
        elem.attachEvent('on' + type, function () {
            handler.call(elem);
        });
    }
    return false;
}

function loadEventListeners() {
    itemBox = d.querySelectorAll('.item_box');
    basketCont = d.getElementById('basket_content');

    for (var i = 0; i < itemBox.length; i++) {
        addEvent(itemBox[i].querySelector('.add_item'), 'click', addToBasket);
    }

    addEvent(d.getElementById('checkout'), 'click', processOrder);
    addEvent(d.getElementById('clear_basket'), 'click', clearBasket);

    if (user.role === "ADMIN") {
        $("#new-book").show();
        addEvent(d.getElementById('add-book'), 'click', addBook);
    } else $("#new-book").hide();
}


function addToBasket(e) {
    this.disabled = true; //
    var basketData = getbasketData() || {},
        parentBox = this.closest('tr'),
        itemId = this.getAttribute('data-id'),
        itemTitle = parentBox.querySelector('.item_title').innerHTML,
        itemPrice = parentBox.querySelector('.item_price').innerHTML;
    if (basketData.hasOwnProperty(itemId)) {
        basketData[itemId][2] += 1;
    } else {
        basketData[itemId] = [itemId, itemTitle, 1];
    }
    if (!setbasketData(basketData)) {
        this.disabled = false;
    }
    openBasket(e);
    return false;
}


function openBasket(e) {
    var basketData = getbasketData(),
        totalItems = '';
    if (basketData !== null) {
        totalItems = '<table class="table table-hover shopping_list"><tr><th>id</th><th>Name</th><th>Count</th></tr>';
        for (var items in basketData) {
            totalItems += '<tr>';
            for (var i = 0; i < basketData[items].length; i++) {
                totalItems += '<td>' + basketData[items][i] + '</td>';
            }
            totalItems += '</tr>';
        }
        totalItems += '</table>';
        basketCont.innerHTML = totalItems;
    } else {
        basketCont.innerHTML = '<h2>The basket is empty!</h2>';
    }
    return false;
}

function processOrder() {
    var basketData = getbasketData()
    var booksInBasket;
    if (basketData !== null) {
        booksInBasket = [];
        for (var items in basketData) {
            var data = basketData[items];
            booksInBasket.push({
                "id": data[0],
                "name": data[1],
                "count": data[2]
            });
        }
        $.ajax({
            url: corsurl+'/buy',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(booksInBasket),
            contentType: 'application/json; charset=utf-8',
            beforeSend: function (request) {
                request.setRequestHeader("token", getCookie("Auth-Token"));
            },
            success: function (data) {
                appendData(data);
                loadEventListeners();
                clearBasket(this);
                openBasket(this);
            }
        })
    }
}


function clearBasket(e) {
    localStorage.removeItem('basket');
    basketCont.innerHTML = '<h2>The basket is empty!</h2>';
}


function getbasketData() {
    return JSON.parse(localStorage.getItem('basket'));
}


function setbasketData(o) {
    localStorage.setItem('basket', JSON.stringify(o));
    return false;
}


function appendData(data) {
    $.each(data, function (index, element) {
        if (index === "allowedBooks") {
            dom_elem = $("#books");
            dom_elem.empty();
            dom_elem.append($("<tr>")
                .append($("<th>")
                    .text("#")
                    .attr("scope", "col"))
                .append($("<th>")
                    .text("Name")
                    .attr("scope", "col"))
                .append($("<th>")
                    .text("Price")
                    .attr("scope", "col"))
                .append($("<th>")
                    .text("Times Bought")
                    .attr("scope", "col"))
                .append($("<th>")
                    .text("Action")
                    .attr("scope", "col"))
            );
            for (i = 0; i < element.length; i++) {
                dom_elem.append($("<tr>")
                    .append($("<td>")
                        .attr("class", "item_id")
                        .text(i + 1)
                    ).append($("<td>")
                        .attr("class", "item_title")
                        .text(element[i].name)
                    ).append($("<td>")
                        .attr("class", "item_price")
                        .text(element[i].price)
                    ).append($("<td>")
                        .attr("class", "item_timesBought")
                        .text(element[i].timesBought)
                    ).append($("<td>")
                        .append($("<button>")
                            .attr("class", "btn btn-info add_item")
                            .attr("data-id", element[i].id)
                            .text("Buy")
                        )).attr("class", "item_box")
                );
            }
        } else if (index === "currentUser") {
            user = element;
            user_name = $("#user-name");
            user_name.empty();
            user_name.text("Welcome, " +element.name+"      ");

            user_earned_money = $("#user-earned-money");
            user_earned_money.empty();
            user_earned_money.text("Earned Money: "+element.earnedMoney + "$        ");
        }
    });
}


function addBook() {

    var book = new Object();

    book.name = $("#bookName").val();
    book.price = $("#bookPrice").val();

    if(book.name !='' && book.price!='') {
        $.ajax({
            url: corsurl+'/addBook',
            type: 'POST',
            dataType: 'json',
            data: JSON.stringify(book),
            contentType: 'application/json; charset=utf-8',
            beforeSend: function (request) {
                request.setRequestHeader("token", getCookie("Auth-Token"));
            },
            success: function (data) {
                appendData(data);
                loadEventListeners();
                openBasket(this);
            }
        })
    }
}

function logout(){
    $.ajax({
        url: corsurl+'/logout/',
        type: 'DELETE',
        beforeSend: function (request) {
            request.setRequestHeader("token", getCookie("Auth-Token"));
        },
        success: function (data, textStatus, request) {
            delete_cookie("Auth-Token");
            window.location.href = '/registration.html';
        },
        error: function (request, textStatus, errorThrown) {
            console.log("Error logout!")
        }
    })
}

function delete_cookie (name) {
    document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
};

function getCookie(name) {
    let matches = document.cookie.match(new RegExp(
        "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
    ));
    return matches ? decodeURIComponent(matches[1]) : undefined;
}