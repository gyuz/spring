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

function checkFields(form) {
    var pass = false;
    pass = checkDate("birthDate", false);
    if(pass) {
        pass = checkDate("dateHired", true);
        if(pass){
            var regex = /^[a-zA-Z ]*$/;
            var firstName = document.getElementsByName("firstName")[0];
            if(firstName.value.match(regex)){
                var middleName = document.getElementsByName("middleName")[0];
                if(middleName.value.match(regex)){
                    var lastName = document.getElementsByName("lastName")[0];
                    if(!lastName.value.match(regex)){
                        msg(lastName);
                    }
                } else {
                    msg(middleName);
                }
            } else {
                msg(firstName);
                return false;
            }   
       }
  } 
  alert(pass);  
  return pass;
}

function msg(element){
    element.focus();
    alert("Characters A-Z only!");
}

function checkDate(field, allowBlank) {
    var minYear = 1800;
    var maxYear = (new Date().getFullYear());
    var errorMsg = "";
    var element = document.getElementsByName(field)[0];
    re = /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;

    if(element.value != "") {  
      if(regs = element.value.match(re)) {
        if(regs[2] < 1 || regs[2] > 31) {
          errorMsg = "Invalid value for day: " + regs[2];
        } else if(regs[1] < 1 || regs[1] > 12) {
          errorMsg = "Invalid value for month: " + regs[1];
        } else if(regs[3] < minYear || regs[3] > maxYear) {
          errorMsg = "Invalid value for year: " + regs[3] + " - must be between " + minYear + " and " + maxYear;
        }
      } else {
        errorMsg = "Invalid date format. Must be MM/DD/YYYY";
      }
    } else if(!allowBlank) {
      errorMsg = "Empty date not allowed!";
    }

    if(errorMsg != "") {
      alert(errorMsg);
      element.focus();
      return false;
    }

    return true;
}
