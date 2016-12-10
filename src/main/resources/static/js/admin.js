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


