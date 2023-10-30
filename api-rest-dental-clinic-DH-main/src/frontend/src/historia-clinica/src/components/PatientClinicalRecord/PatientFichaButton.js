// Código en el componente del botón "Ficha Paciente"
import React, { useState } from 'react';
import Modal from 'react-modal';
import PatientDetails from './PatientDetails'; // Componente para mostrar los detalles del paciente
import PatientMedications from './PatientMedications'; // Componente para mostrar los medicamentos del paciente
import PatientPhysicalExams from './PatientPhysicalExams'; // Componente para mostrar los exámenes físicos del paciente
import PatientDiagnoses from './PatientDiagnoses'; // Componente para mostrar los diagnósticos del paciente

const PatientFichaButton = ({ patientId }) => {
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [selectedSection, setSelectedSection] = useState(null);

  const openModal = (section) => {
    setSelectedSection(section);
    setModalIsOpen(true);
  };

  const closeModal = () => {
    setSelectedSection(null);
    setModalIsOpen(false);
  };

  return (
    <div>
      <button onClick={() => openModal('details')}>Datos del paciente</button>
      <button onClick={() => openModal('medications')}>Medicamentos</button>
      <button onClick={() => openModal('physical-exams')}>Examen Fisico</button>
      <button onClick={() => openModal('diagnoses')}>Diagnostico</button>

      <Modal isOpen={modalIsOpen} onRequestClose={closeModal}>
        {selectedSection === 'details' && <PatientDetails patientId={patientId} />}
        {selectedSection === 'medications' && <PatientMedications patientId={patientId} />}
        {selectedSection === 'physical-exams' && <PatientPhysicalExams patientId={patientId} />}
        {selectedSection === 'diagnoses' && <PatientDiagnoses patientId={patientId} />}
        <button onClick={closeModal}>Cerrar</button>
      </Modal>
    </div>
  );
};

export default PatientFichaButton;
