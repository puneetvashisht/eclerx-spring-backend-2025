import { useState } from "react";

const Suggestions = () => {

    const [message, setMessage] = useState('')

    const handleMessageChange = (e)=> {
       setMessage(e.target.value)
    }

    const suggestions= () => {
         fetch('http://localhost:8080/api/chat?message=' + message)
        .then(res=>res.json())
        .then(data => {
            console.log(data);
        })
        .catch(err=> console.log(err));

    }

    return (
        <>
        <h2>Suggestions Component</h2>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Destination</span>
            <input onBlur={suggestions} onChange={handleMessageChange} value={message} type="text" class="form-control" placeholder="Take Trip Suggestions here..." aria-label="Username" aria-describedby="basic-addon1"/>
            </div>

        </>
    )
}
export default Suggestions;