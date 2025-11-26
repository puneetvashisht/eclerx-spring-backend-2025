import { useState } from "react";

const AddTrip = () => {

    const [destination, setDestination] = useState('')
    const [startDate, setStartDate] = useState('')
    const [endDate, setEndDate] = useState('')

    const handleChangeDestination = (e)=>{
        setDestination(e.target.value);
    }
    const handleChangeStartDate = (e)=>{
        setStartDate(e.target.value);
    }
    const handleChangeEndDate = (e)=>{
        setEndDate(e.target.value);
    }

    const addTrip = () => {
        console.log('Adding a trip..')
        const trip = {destination, startDate, endDate};

        fetch('http://localhost:9090/api/trips', {
            method: "POST",
            headers: {
                'Content-Type': "application/json"
            },
            body: JSON.stringify(trip)
        })
        .then(res => {
            console.log(res.status)
        })

        
    }

    return (
        <>
            <h2>Add Trip Component</h2>

            <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Destination</span>
            <input onChange={handleChangeDestination} value={destination} type="text" class="form-control" placeholder="Enter Destination" aria-label="Username" aria-describedby="basic-addon1"/>
            </div>

            <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Start Date</span>
            <input onChange={handleChangeStartDate} value={startDate} type="date" class="form-control"  aria-label="Username" aria-describedby="basic-addon1"/>
            </div>

            <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">End Date</span>
            <input onChange={handleChangeEndDate} value={endDate} type="date" class="form-control"  aria-label="Username" aria-describedby="basic-addon1"/>
            </div>

            <button onClick={addTrip} type="button" class="btn btn-primary">Add Trip</button>

        </>
    )
}

export default AddTrip;