$(document).ready(function () {
    updateSolutionEdition();

    $("#contractForm").submit(function (event) {
        event.preventDefault();
        fire_ajax_contract_submit();
    });

    $("#solution").change(function (event){
        updateSolutionEdition();
    });


});

function fire_ajax_contract_submit() {

    var contractData = {}
    contractData["customerUid"] = $("#customerUid").val();
    contractData["solution"] = $("#solution").val();
    contractData["solutionEdition"] = $("#solutionEdition").val();
    contractData["manageable"] = $('input[name="manageable"]').val();
    contractData["startDate"] = $("#startDate").val();
    contractData["endDate"] = $("#endDate").val();
//    contractData["contractType"] = $("#contractType").val();
//    contractData["orderEntries"] = $("#orderEntries").val();
    contractData["partnerContactUid"] = $("#partnerContactUid").val();


    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/contract/add",
        data: JSON.stringify(contractData),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var html = '';
            var len = data.contractDTOList.length;
            for ( var i = 0; i < len; i++) {
                html += '<li>'
                + data.contractDTOList[i].uid + '   ' + data.contractDTOList[i].contractType + '   ' +
                data.contractDTOList[i].solutionEdition + '</li>';
             }
            $('#contracts').html(html);
            $("#btn-search").prop("disabled", false);
        },
        error: function (e) {
            $('#feedback').html(json);
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
        }
    });
}

function updateSolutionEdition() {

    console.log($("#solution").val());

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/solutionEdition",
        data: JSON.stringify($("#solution").val()),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var html = '';
            var len = data.solutionEditions.length;
            for ( var i = 0; i < len; i++) {
            	html += '<option value="' + data.solutionEditions[i] + '">'
            	+ data.solutionEditions[i] + '</option>';
            }
            $('#solutionEdition').html(html);
        },
        error: function (e) {
            $('#feedback').html(json);
            console.log("ERROR : ", e);
        }
    });
}
