/**
 * Created by hootting on 2016. 12. 5..
 */

function updateapp(appidx,check) {
    var jsontest={"isregister" : check};
    $.ajax({
        url: 'http://localhost:8080/api/apps/' + appidx,
        method: 'PATCH',
        contentType: "application/json",
        data: JSON.stringify(jsontest)
    }).done(function () {
        location.reload()
    }).fail(function(){
        console.log('fail')
    })

}
function reportok(reportidx) {
    $.ajax({
        url: 'http://localhost:8080/report/' + reportidx,
        method: 'GET'
    }).done(function () {
        location.reload()
    }).fail(function(){
        console.log('fail')
    })

}

function reportclose(reportidx) {
    $.ajax({
        url: 'http://localhost:8080/report/' + reportidx+'/no',
        method: 'GET'
    }).done(function () {
        location.reload()
    }).fail(function(){
        console.log('fail')
    })

}

function slangdelete(slangidx){
    $.ajax({
        url: 'http://localhost:8080/api/slangs/' + slangidx,
        method: 'DELETE'
    }).done(function () {
        location.reload()
    }).fail(function(){
        console.log('fail')
    })
}

function picdelete(badpicidx){
    $.ajax({
        url: 'http://localhost:8080/api/badpics/' + badpicidx,
        method: 'DELETE'
    }).done(function () {
        location.reload()
    }).fail(function(){
        console.log('fail')
    })
}


function addslang(){
    var data={
        'word' : $("#word").val()
    }
    $.ajax({
        url:'/api/slangs',
        method:'POST',
        contentType: "application/json",
        data:JSON.stringify(data)
    }).done(function(value){
        location.reload();
    })
}