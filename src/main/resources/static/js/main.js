/**
 * Created by hootting on 2016. 12. 10..
 */
function register(){
    var data={
        'company' :"/api/companies/"+$("#companyIdx").val(),
        'name' : $("#name").val(),
        'type' : $("#type").val()
        }

    $.ajax({
        url:'/api/apps',
        method:'POST',
        contentType: "application/json",
        data:JSON.stringify(data)
    }).done(function(value){
        location.reload();
    })
}



function updateapp(appidx) {
    var jsontest={"isrequestclose" : true};
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