function addRow(id, rowIndex, tag){
    var table = document.getElementById(id);
    var new_row = table.rows[rowIndex].cloneNode(true);
    var inp = new_row.cells[0].getElementsByTagName("input")[0];
    inp.value = "";
    new_row.cells[1].innerHTML = "";
    if(tag != "person"){
        var inp1 = new_row.cells[2].getElementsByTagName("input")[0];
        inp1.value="";
    } else {
        new_row.style.display = "inherit";
    }
    if(id == "newContacts") {
        var inp2 = new_row.cells[3].getElementsByTagName("input")[0];
        inp2.value="";
    }
    table.appendChild(new_row);
}

function getSelectedValue(rowid){
    document.getElementById("personId").value = document.getElementById("personId"+rowid).value;
    document.forms["personList"].sumbit();
}

function deleteRow(row, table, formName, rowId) {
    var form = document.forms[formName];
    var i = row.parentNode.parentNode.rowIndex;
    if(document.getElementById(table).rows[i].cells[1].innerHTML != ""){
        var input = document.createElement("input");
        input.type = "hidden";
        input.name = table+"Deleted";
        input.value = rowId;
        form.appendChild(input);
    }
    document.getElementById(table).deleteRow(i);
}
