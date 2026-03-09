function signup(){
    let user=document.getElementById("username").value;
    let pass=document.getElementById("password").value;

    localStorage.setItem("username",user);
    localStorage.setItem("password",pass);

    alert("Signup Successful");
}

function login(){
    let user=document.getElementById("username").value;
    let pass=document.getElementById("password").value;

    if(user===localStorage.getItem("username") &&
       pass===localStorage.getItem("password")){

        document.getElementById("authSection").style.display="none";
        document.getElementById("dashboard").style.display="block";

        displayHistory();

    }else{
        alert("Invalid Credentials");
    }
}

function logout(){
    location.reload();
}

function bookAppointment(){

    let name=document.getElementById("patientName").value;
    let doctor=document.getElementById("doctor").value;
    let slot=document.getElementById("slot").value;
    let payment=document.getElementById("paymentMethod").value;

    let token=Math.floor(Math.random()*900)+100;

    document.getElementById("tokenResult").innerHTML=
    `Token Number: ${token}<br>Payment Successful via ${payment}`;

    let history=JSON.parse(localStorage.getItem("history")) || [];

    history.push({name,doctor,slot,token,payment});

    localStorage.setItem("history",JSON.stringify(history));

    displayHistory();
}

function displayHistory(){

    let history=JSON.parse(localStorage.getItem("history")) || [];
    let rows="";

    history.forEach(item=>{
        rows+=`
        <tr>
            <td>${item.name}</td>
            <td>${item.doctor}</td>
            <td>${item.slot}</td>
            <td>${item.token}</td>
            <td>${item.payment}</td>
        </tr>
        `;
    });

    document.getElementById("history").innerHTML=rows;
}
