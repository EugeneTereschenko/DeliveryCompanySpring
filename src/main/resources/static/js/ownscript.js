function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

$(document).ready(function(){
    $("#id_price_count").click(function () {

        var delivery = {};

        var cityfromid = $('#cityfromid').val();
        var citytoid = $('#citytoid').val();
        var typeOfdeli = $('#typetoid').val();
        var count = $('#count').val();
        var weight = $('#weight').val();
        var length = $('#length').val();
        var width = $('#width').val();
        var height = $('#height').val();

        delivery.cityfromid = cityfromid;
        delivery.citytoid = citytoid;
        delivery.typedeliverid = typeOfdeli;
        delivery.count = count;
        delivery.weight = weight;
        delivery.length = length;
        delivery.width = width;
        delivery.height = height;


        console.log(delivery);

        $.ajax({
            url: '/cart/countdelivery',
            type: 'POST',
            data: JSON.stringify(delivery),
            dataType: 'JSON',
            contentType: "application/json",
            beforeSend: function (x) {
                if (x && x.overrideMimeType) {
                    x.overrideMimeType("application/json;charset=UTF-8");
                }
            },
            success: function (data) {
                console.log(data);
                console.log('Price: ' + data.toString() + ' $');
                $("#sentstatus_edit").html("Price: " + data.price + " $");

            },
            failure: function (data) {
                console.log(data);

                $("#sentstatus_edit").html("error");
            }
        });
    });

    $("#id_brought_count").click(function () {

        var delivery = {};

        var cityfromid = $('#cityfromid').val();
        var citytoid = $('#citytoid').val();
        var typeOfdeli = $('#typetoid').val();
        var count = $('#count').val();
        var weight = $('#weight').val();
        var length = $('#length').val();
        var width = $('#width').val();
        var height = $('#height').val();

        var useridtemp = getCookie("userid");

        delivery.userid = useridtemp;
        delivery.cityfromid = cityfromid;
        delivery.citytoid = citytoid;
        delivery.typedeliverid = typeOfdeli;
        delivery.count = count;
        delivery.weight = weight;
        delivery.length = length;
        delivery.width = width;
        delivery.height = height;

        console.log(delivery);

        $.ajax({
            url: '/cart/putdelivery',
            type: 'POST',
            data: JSON.stringify(delivery),
            contentType: "application/json",
            beforeSend: function (x) {
                if (x && x.overrideMimeType) {
                    x.overrideMimeType("application/json;charset=UTF-8");
                }
            },
            success: function (data) {
                console.log(data);
                $('<div/>',{'id':'TextBoxDiv' + data.cartid })
                    .html($('<div class="d-flex flex-row justify-content-between align-items-center p-2 bg-white mt-4 px-3 rounded">')
                        .html($('<div class="d-flex flex-column align-items-center product-details">')
                            .html($('<span class="font-weight-bold">' + data.citiesname + '</span>'))
                            .append($('<div class="d-flex flex-row product-desc">')
                                .html($('<div class="size mr-1">')
                                    .html('<span class="text-grey">Distance:</span><span class="font-weight-bold">' + data.distancebetween + 'km</span>'))
                                .append($('<div class="color">')
                                    .html('<span class="text-grey">Time:</span><span class="font-weight-bold">17:00-23:00</span>')
                                )))
                        .append($('<div class="d-flex flex-row align-items-center qty">').html('<button id=' + data.cartid + ' class="btn btn-default iterdowncost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-left-fill" viewBox="0 0 16 16">\n' +
                            '  <path d="m3.86 8.753 5.482 4.796c.646.566 1.658.106 1.658-.753V3.204a1 1 0 0 0-1.659-.753l-5.48 4.796a1 1 0 0 0 0 1.506z"/>\n' +
                            '</svg></button>'))
                        .append($('<div class="d-flex flex-row align-items-center qty">').html('<h5 id="textcount' + data.cartid + '" class="text-grey mt-1 mr-1 ml-1">' + count + '</h5>'))
                        .append($('<div class="d-flex flex-row align-items-center qty">').html('<button id=' + data.cartid + ' class="btn btn-default itercost" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16">\n' +
                            '  <path d="m12.14 8.753-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z"/>\n' +
                            '</svg></button>'))
                        .append($('<div>').html('<h5 class="text-grey">' + data.shippingprice + '$</h5>'))
                        .append($('<div class="d-flex align-items-center">').html('<button id=' + data.cartid + ' class="btn btn-primary iterdeletecost" rel="tooltip" ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">\n' +
                            '                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>\n' +
                            '                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>\n' +
                            '                </svg></button>'))
                    )
                    .appendTo( '#TextBoxesGroup' )



            },
            failure: function (data) {
                console.log(data);
            }
        });


    });


    $("#TextBoxesGroup").on("click", ".iterdeletecost", function () {
        console.log(this.id);

        var id = this.id

        $.ajax({
            url: "/cart/removedelivery/" + id,
            type: 'DELETE',
            contentType: "application/json",
            dataType : 'json',
            success: function (data) {
                console.log(data);

                console.log(id);
                $("#TextBoxDiv" + id).remove();
            },
            failure: function (data) {

            }
        });

    });


    $("#TextBoxesGroup").on("click", ".iterdowncost", function () {
        console.log(this.id);

        var id = this.id
        var count = parseInt($('#textcount' + id).text());
        count = count - 1;

        var delivery = {};
        delivery.id = id;
        delivery.count = count;

        $.ajax({
            url: '/cart/updatenumdelivery',
            type: 'PUT',
            data: JSON.stringify(delivery),
            contentType: "application/json",
            beforeSend: function (x) {
                if (x && x.overrideMimeType) {
                    x.overrideMimeType("application/json;charset=UTF-8");
                }
            },
            success: function (data) {
                console.log(data);

                $('#textcount' + id).text(count);

            },
            failure: function (data) {

            }
        });

    });



    $("#TextBoxesGroup").on("click", ".itercost", function () {
        console.log(this.id);

        var id = this.id
        var count = parseInt($('#textcount' + id).text());
        count = count + 1;

        var delivery = {};
        delivery.id = id;
        delivery.count = count;

        $.ajax({
            url: '/cart/updatenumdelivery',
            type: 'PUT',
            data: JSON.stringify(delivery),
            contentType: "application/json",
            beforeSend: function (x) {
                if (x && x.overrideMimeType) {
                    x.overrideMimeType("application/json;charset=UTF-8");
                }
            },
            success: function (data) {
                console.log(data);

                $('#textcount' + id).text(count);

            },
            failure: function (data) {

            }
        });

    });



    $("#id_proceed_payment").click(function () {
        window.location.replace("http://localhost:8080/cart/showdelivery");
    });


    $("#id_checkout_login").click(function () {

        var userid = getCookie("userid");

        var delivery = {};
        var firstname = $('#firstname').val();
        var lastname = $('#lastname').val();
        var address = $('#address').val();
        var phone = $('#phone').val();
        var country = $('#country').val();
        var city = $('#state').val();
        var zip = $('#zip').val();

        var sameaddress = $('#sameaddress').val();
        //var saveinfo = $('#saveinfo').val();


        delivery.firstname = firstname;
        delivery.lastname = lastname;
        delivery.address = address;
        delivery.phone = phone;
        delivery.country = country;
        delivery.city = city;
        delivery.zip = zip;
        delivery.userid = userid;

        delivery.sameaddress = sameaddress;
        delivery.saveinfo = saveinfo;

        //var sendFlagaddress = 0;
        //var sendFlagcard = 0;

        $.ajax({
            url: '/cart/addressdata',
            type: 'POST',
            data: JSON.stringify(delivery),
            contentType: "application/json",
            beforeSend: function (x) {
                if (x && x.overrideMimeType) {
                    x.overrideMimeType("application/json;charset=UTF-8");
                }
            },
            success: function (data) {
                console.log(data);

                //sendFlagaddress = 1;

            },
            failure: function (data) {

            }
        });

        var paymentMethod;
        var jsondata2 = {};
        console.log()

        var credname = $('#cc-name').val();
        var crednumber = $('#cc-number').val();
        var credexpiration = $('#cc-expiration').val();
        var credcvv = $('#cc-cvv').val();


        if(document.getElementById('credit').checked) {
            paymentMethod = "credit";

        }else if(document.getElementById('debit').checked) {
            paymentMethod = "debit";

        }else if(document.getElementById('paypal').checked){
            paymentMethod = "paypal";

        }
        jsondata2.name = credname;
        jsondata2.cardnumber = crednumber;
        jsondata2.expirationmonthyear = credexpiration;
        jsondata2.cvv = credcvv;
        jsondata2.userid = userid;
       // jsondata2.paymentmethod = paymentMethod;

        $.ajax({
            url: '/cart/paymentdata',
            type: 'POST',
            data: JSON.stringify(jsondata2),
            contentType: "application/json",
            beforeSend: function (x) {
                if (x && x.overrideMimeType) {
                    x.overrideMimeType("application/json;charset=UTF-8");
                }
            },
            success: function (data) {
                console.log(data);
                //sendFlagcard = 1;
                //window.location.replace("http://localhost:8080/success");

            },
            failure: function (data) {

            }
        });



        $.ajax({
            url: '/cart/updatedatacart/' + userid,
            type: 'GET',
            contentType: "application/json",
            dataType : 'json',
            success: function (data) {
                console.log(data);

               // if ((sendFlagaddress == 1)&&(sendFlagcard == 1)) {

                    window.location.replace("http://localhost:8080/success");
                //}

            },
            failure: function (data) {

            }
        });

    });

    $(".pdfreport").click(function () {

        console.log("test");


        var url = "http://localhost:8080/cart/createpdfdoc/" + this.id;

        window.open(url, '_blank');
    });

    $("#idusersitemy").click(function () {
        console.log("test");
        window.location.replace("http://localhost:8080/api/showusers");
    });

    $("#idcartsitemy").click(function () {
        console.log("test");
        window.location.replace("http://localhost:8080/api/showcarts");
    });


});