import { useState } from "react";

const Suggestions = () => {

    const [message, setMessage] = useState('')
    const [suggestion, setSuggestion] = useState('')
    const [country, setCountry] = useState('')
    const [cities, setCities] = useState([])

    const handleMessageChange = (e)=> {
       setMessage(e.target.value)
    }
    const handleCountryChange = (e)=> {
       setCountry(e.target.value)
    }



async function fetchWithTimeout(url, options = {}) {
  const { timeout = 8000 } = options; // Default timeout of 8 seconds
  const controller = new AbortController();
  const timer = setTimeout(() => controller.abort(), timeout);

  try {
    const response = await fetch(url, {
      ...options,
      signal: controller.signal,
    });
    return response;
  } finally {
    clearTimeout(timer); // Clear the timeout if the fetch completes before the timeout
  }
}

    const suggestions= () => {
        //  fetch('http://localhost:8080/api/chat?message=' + message)
        // .then(res=>res.json())
        // .then(data => {
        //     console.log(data);
        // })
        // .catch(err=> console.log(err));

        fetchWithTimeout('http://localhost:8080/api/chat?message=' + message, { timeout: 30000 }) // 10-second timeout
  .then(response => response.text())
  .then(data => {
    console.log(data)
    setSuggestion(data)
  })
  .catch(error => {
    if (error.name === 'AbortError') {
      console.error('Fetch request timed out:', error);
    } else {
      console.error('Fetch error:', error);
    }
  });

    }

    const citiesSuggestion= () => {

         fetchWithTimeout('http://localhost:8080/api/chat-bean?message=' + country, { timeout: 30000 }) // 10-second timeout
        .then(response => response.json())
        .then(data => {
            console.log(data)
            // setSuggestion(data)
            setCities(data.cities)
        })
        .catch(error => {
            if (error.name === 'AbortError') {
            console.error('Fetch request timed out:', error);
            } else {
            console.error('Fetch error:', error);
            }
        });
    }

    return (
        <>
        <h2>Suggestions Component</h2>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Destination</span>
            <input onBlur={suggestions} onChange={handleMessageChange} value={message} type="text" class="form-control" placeholder="Take Trip Suggestions here..." aria-label="Username" aria-describedby="basic-addon1"/>
            
            <pre>{suggestion}</pre>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Cities Of Country</span>
            <input onBlur={citiesSuggestion} onChange={handleCountryChange} value={country} type="text" class="form-control" placeholder="Take Trip Suggestions here..." aria-label="Username" aria-describedby="basic-addon1"/>
            
            <pre>{JSON.stringify(cities)}</pre>
        </div>
        </>

        
    )
}
export default Suggestions;