$(document).ready(function () {
    updateSolutionEdition();
    setDefaultValues();
    $("#contractForm").submit(function (event) {
        event.preventDefault();
        fire_ajax_contract_submit();
    });

    $("#orderForm").submit(function (event) {
        event.preventDefault();
        fire_ajax_order_submit();
    });

    $("#solution").change(function (event) {
        event.preventDefault();
        updateSolutionEdition();
    });

    $("#orderType").change(function (event) {
        event.preventDefault();
        updatePredecessorsList();
    });


});

function fire_ajax_contract_submit() {

    var contractData = {};
    contractData["customerUid"] = $("#customerUid").val();
    contractData["solution"] = $("#solution").val();
    contractData["solutionEdition"] = $("#solutionEdition").val();
    contractData["manageable"] = $('input[name="manageable"]').val();
    contractData["startDate"] = $("#startDate").val();
    contractData["endDate"] = $("#endDate").val();
    contractData["contractType"] = $("#contractType").val();
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
            for (var i = 0; i < len; i++) {
                html += '<li data-id="' + data.contractDTOList[i].uid + '" data-contact="' +
                    data.contractDTOList[i].partnerContactUid + '">'
                    + data.contractDTOList[i].uid + '   '
                    + data.contractDTOList[i].contractType + '   '
                    + data.contractDTOList[i].solutionEdition + '</li>';
            }
            $('#contracts').html(html);
            updatePredecessorsList();
            $("#btn-search").prop("disabled", false);
        },
        error: function (e) {
            $('#feedback').html(json);
            console.log("ERROR : ", e);
            $("#btn-search").prop("disabled", false);
        }
    });
}

function fire_ajax_order_submit() {

    let orderData = {};

    orderData["orderType"] = $("#orderType").val();
    orderData["predecessorId"] = $("#predecessorId").val();
    orderData["orderEntries"] = $("#orderEntries").val();
    orderData["entriesPrice"] = $("#entriesPrice").val();
    orderData["orderStatus"] = $("orderStatus").val();


    $("#btn-search").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/order/add",
        data: JSON.stringify(orderData),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            let html = '';
            let lenContracts = data.contractDTOList.length;
            for (let i = 0; i < lenContracts; i++) {
                html += '<li data-id="' + data.contractDTOList[i].uid + '">'
                    + data.contractDTOList[i].uid + '   '
                    + '</li>';

            }
            $('#orders').html(html);
            updatePredecessorsList();
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
            for (var i = 0; i < len; i++) {
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

function updatePredecessorsList() {
    var htmlPredecessor = '';
    if ($("#orderType").val() === 'INITIAL') {
        let list = $('#contracts').children();
        list.toArray().forEach(function (item, index) {
            htmlPredecessor += '<option value="' + item.getAttribute('data-id') + '">'
                + item.getAttribute('data-id') + '</option>';
        });
    } else {
        let list = $('#orders').children();
        list.toArray().forEach(function (item, index) {
            htmlPredecessor += '<option value="' + item.getAttribute('data-id') + '">'
                + item.getAttribute('data-id') + '</option>';
        });
    }
    $('#predecessorId').html(htmlPredecessor);
}

function setDefaultValues() {
    $("#orderEntries").val("1");
    $("#entriesPrice").val("0.00");
    let dateNow = new Date();
    dateNow.setSeconds(0, 0);
    let dateNowFormatted = dateNow.toISOString().replace(/:00.000Z/, "");
    let oneYearFromNow = new Date();
    oneYearFromNow.setFullYear(oneYearFromNow.getFullYear() + 1);
    oneYearFromNow.setSeconds(0, 0);
    let oneYearFromNowFormatted = oneYearFromNow.toISOString().replace(/:00.000Z/, "");
    $('#startDate').val(dateNowFormatted);
    $('#endDate').val(oneYearFromNowFormatted);
}
