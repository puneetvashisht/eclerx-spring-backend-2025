//1. fetch the courses from REST API
//2. display the courses in tabular format
import {useState, useEffect} from 'react';
function ViewTrips(){

    useEffect(()=>{
        //fetch the trips from REST API
        fetch('http://localhost:9090/api/trips')
        .then((response)=>response.json())
        .then(data => {
            console.log(data);
            setTrips(data);
        })

    },[]);

    const [trips, setTrips] = useState([]);

    const tripRows = trips.map((trip, i)=> (
        <tr key={trip.id}>
            <th scope="row">{i+1}</th>
            <td>{trip.destination}</td>
            <td>{trip.startDate}</td>
            <td>{trip.endDate}</td>
        </tr>
    ));

    return (
        <table className="table">
        <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">Destination</th>
            <th scope="col">Start Date</th>
            <th scope="col">End Date</th>
            </tr>
        </thead>
        <tbody>
            {tripRows}
        </tbody>
        </table>
    )
}
export default ViewTrips;