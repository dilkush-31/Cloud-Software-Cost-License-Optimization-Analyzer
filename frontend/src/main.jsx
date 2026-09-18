import React, {useEffect, useState} from 'react';
import {createRoot} from 'react-dom/client';
import axios from 'axios';
import './style.css';

const API='http://localhost:8080/api';

function App(){
  const [summary,setSummary]=useState({});
  const [findings,setFindings]=useState([]);

  const load=async()=>{
    const s=await axios.get(API+'/optimization/summary'); setSummary(s.data);
    const f=await axios.get(API+'/optimization/findings'); setFindings(f.data);
  };
  const analyze=async()=>{ await axios.post(API+'/optimization/analyze'); load(); };

  useEffect(()=>{load()},[]);

  return <div className="app">
    <h1>Cloud Cost & License Optimizer</h1>
    <div className="cards">
      <div><span>Cloud Resources</span><b>{summary.cloudResources ?? 0}</b></div>
      <div><span>Software Licenses</span><b>{summary.softwareLicenses ?? 0}</b></div>
      <div><span>Findings</span><b>{summary.optimizationFindings ?? 0}</b></div>
      <div><span>Potential Annual Savings</span><b>₹{Number(summary.potentialAnnualSavings||0).toLocaleString('en-IN')}</b></div>
    </div>
    <button onClick={analyze}>Run Optimization Analysis</button>
    <h2>Optimization Findings</h2>
    <table><thead><tr><th>Category</th><th>Item</th><th>Issue</th><th>Severity</th><th>Annual Saving</th></tr></thead>
    <tbody>{findings.map(f=><tr key={f.id}><td>{f.category}</td><td>{f.resourceOrSoftware}</td><td>{f.issue}</td><td>{f.severity}</td><td>₹{Number(f.estimatedAnnualSaving||0).toLocaleString('en-IN')}</td></tr>)}</tbody>
    </table>
  </div>
}
createRoot(document.getElementById('root')).render(<App/>);
