$(document).ready(function() {
    $("#filterButton").click(function() {
        var value = $("#filterInput").val();
        var filterBy = $("#filterSelect").val();

        $.get("/index/" + filterBy + "/" + value, function(data) {
            updateDocumentsTable(data);
        });
    });
    $("#resetFilterButton").click(function() {
        $.get("/index", function(data) {
            updateDocumentsTable(data);
        });
    });

function updateDocumentsTable(filteredData) {
    $("tbody").empty();
    if (filteredData.length === 0) {
        $("tbody").append("<tr><td colspan='4'>Нет записей, удовлетворяющих критериям фильтрации</td></tr>");
    } else {
        if (filteredData.id) {
            setDocumentsTable(filteredData);
        } else {
            filteredData.forEach((item) => { setDocumentsTable(item) });
        }
    }
}
function setDocumentsTable(item) {
    var row = `<tr><td>${item.id}</td><td>${item.name}</td><td>${item.type}</td><td>${item.date}</td></tr>`
    $("tbody").append(row);
}
});
function openWindow() {
    document.getElementById('popupWindow').style.display = 'block';
}

function closeWindow() {
    document.getElementById('popupWindow').style.display = 'none';
}

window.onclick = function(event) {
    var popup = document.getElementById('popupWindow');
    if (event.target === popup) {
        popup.style.display = 'none';
    }

}
$('tbody').on('click', '.editDocument', function() {
    let id = $(this).find('td:first').text();
    let name = $(this).find('td:nth-child(2)').text();
    let type = $(this).find('td:nth-child(3)').text();

    $('#popupWindow1 input[placeholder="ID"]').val(id);
    $('#popupWindow1 input[placeholder="Новое название"]').val(name);
    $('#popupWindow1 input[placeholder="Тип документа"]').val(type);

    $('#popupWindow1').show();
});

function saveDocument() {
        var documentData = {
            name: $('#documentName').val(),
            type: $('#documentType').val()
        };
        fetch('/index',
        {   method: 'POST',
              headers: {'Content-Type': 'application/json'},
              body: JSON.stringify(documentData) }
          );
          window.location.href = '/'
    }
function updateDocument() {
        var id = $('#documentId1').val();
        var documentData = {
            id: $('#documentId1').val(),
            name: $('#documentName1').val(),
            type: $('#documentType1').val()
        };
        fetch('/index/' + id,
        {   method: 'PUT',
              headers: {'Content-Type': 'application/json'},
              body: JSON.stringify(documentData) }
          );
          window.location.href = '/'
    }