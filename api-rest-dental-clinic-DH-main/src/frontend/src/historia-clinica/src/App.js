import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import Footer from './components/Footer/Footer';
import Login from './components/Login/Login';
import MedicList from './components/MedicList';
import PatientList from './components/Patient/PatientList';
import CreateMedic from './components/CreateMedic';
import CreatePatient from './components/CreatePatient/CreatePatient';
import DiagnosisTab from './components/DiagnosisTab';
import PatientProfile from './components/PatientProfile';




function App() {
return (
    <div className="app-container">
      <Router>
        <Header />
        <div className="content">
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/medic/list" element={<MedicList />} />
            <Route path="/patient/list" element={<PatientList />} />
            <Route path="/medic/create" element={<CreateMedic />} />
            <Route path="/patient/create" element={<CreatePatient />} />
            <Route path="/patient/:patientId/diagnosis" element={DiagnosisTab} />
            <Route path="/patient/:patientId" element={PatientProfile} />

          </Routes>
        </div>
        <Footer />
      </Router>
    </div>
  );
}
export default App;


