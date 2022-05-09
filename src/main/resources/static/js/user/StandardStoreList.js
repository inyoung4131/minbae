
var page=0;
var user_lat=JSON.parse(window.localStorage.getItem("user_latlng")).user_lat;
var user_lng=JSON.parse(window.localStorage.getItem("user_latlng")).user_lng;
var category='%';
var resultdata;

var data={
    "page":page,
    "user_lat":user_lat,
    "user_lng":user_lng,
    "category":category
}

function requestStandardStoreList(){
alert(JSON.parse(window.localStorage.getItem("user_latlng")).user_lat);
$.ajax({
    type: 'GET',
    url: '/user/category/standard',
    dataType: 'json',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify(data)
}).done(function (data) {
    resultdata=data;
}).fail(function (error) {
    alert(JSON.stringify(error));
});
}