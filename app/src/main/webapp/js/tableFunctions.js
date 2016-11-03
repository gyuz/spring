alert("in");

function addRow(id){
    var table = document.getElementById(id);
    var new_row = table.rows[0].cloneNode(true);
    table.appendChild(new_row);
}

function getSelectedValue(rowid){
    document.getElementById("personId").value=document.getElementById("personId"+rowid).value;
    alert(rowid + "  "+ document.getElementById("personId"+rowid).value);
    document.forms[0].sumbit();
}

function msg(){
    alert("called");
}
