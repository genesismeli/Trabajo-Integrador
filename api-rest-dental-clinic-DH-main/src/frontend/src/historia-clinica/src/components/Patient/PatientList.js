import React, { Component } from 'react';
import EditPatientForm from './EditPatientForm';
import DeletePatientForm from './DeletePatientForm';
import TrashIcon from '../../assets/icons/trash-icon.svg'; // Ruta al archivo SVG del tacho de basura
import PencilIcon from '../../assets/icons/pencil-icon.svg';
import FormIcon from '../../assets/icons/form-icon.svg';
import Modal from 'react-modal';
import './patientList.css';

Modal.setAppElement('#root');

class PatientList extends Component {
  constructor() {
    super();
    this.state = {
      patients: [], // Almacena la lista de pacientes
      selectedPatient: null,
      selectedPatientDelete : null,
      isEditModalOpen: false,
      isDeleteModalOpen: false,
    };
  }

  componentDidMount() {

      fetch('http://localhost:8081/patient/all')
        .then((response) => response.json())
        .then((data) => {
          this.setState({ patients: data }); // Actualiza el estado con la lista de médicos
        })
        .catch((error) => {
          console.error('Error al obtener la lista de pacientes:', error);
        });
    }

    updatePatientList = () => {

        fetch('http://localhost:8081/patient/all')
          .then((response) => response.json())
          .then((data) => {
            this.setState({ patients: data });
          })
          .catch((error) => {
            console.error('Error al obtener la lista de pacientes:', error);
          });
      };

    handleCreatePatientClick = () => {
        // Utiliza window.location.href para redirigir a la página de creación de pacientes
        window.location.href = '/patient/create';
      };

    handleOpenPatientClick = (patientId) => {
      // Utiliza el componente Link para redirigir a la página del paciente
      // La URL se construye dinámicamente utilizando el patientId
      window.location.href = `/patient/${patientId}`;
    };

     openEditModal = (patient) => {
        this.setState({ selectedPatient: patient, isEditModalOpen: true });
     };

     closeEditModal = () => {
        this.setState({ selectedPatient: null, isEditModalOpen: false });
     };

      // Funciones para abrir y cerrar el modal de eliminación
     openDeleteModal = (patient) => {
        this.setState({ selectedPatientDelete: patient, isDeleteModalOpen: true });
     };

     closeDeleteModal = () => {
        this.setState({ selectedPatientDelete: null, isDeleteModalOpen: false });
     };

    handleEditPatientClick = (patient) => {
           this.setState({ selectedPatient: patient });
           this.updatePatientList();
    };
    handleDeletePatientClick = (patient) => {
          this.setState({ selectedPatientDelete: patient });
          this.updatePatientList();

    };

  render() {
    const { patients, selectedPatient, selectedPatientDelete  } = this.state;
    return (
      <div className="patient-list-container">
        <h2>Lista de Pacientes</h2>
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>DNI</th>
              <th>Fecha de Nacimiento</th>
              <th>Sexo</th>
              <th>Dirección</th>
              <th>Teléfono</th>
              <th>Correo Electrónico</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {patients.map((patient) => (
              <tr key={patient.id}>
                <td>{patient.name}</td>
                <td>{patient.lastName}</td>
                <td>{patient.dni}</td>
                <td>{new Date(patient.birthdate).toLocaleDateString()}</td>
                <td>{patient.gender}</td>
                <td>{patient.adress}</td>
                <td>{patient.phone}</td>
                <td>{patient.email}</td>
                <td>
                 <button onClick={() => this.openEditModal(patient)}>
                 <img src={PencilIcon} alt="Editar" width="20" height="20" />
                 </button>
                 <button onClick={() => this.openDeleteModal(patient)}>
                 <img src={TrashIcon} alt="Eliminar" width="20" height="20" />
                 </button>
                 <button onClick={() => this.handleOpenPatientClick(patient.id)}>
                 <img src={FormIcon} alt="Ficha Paciente" width="20" height="20" />
                 </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {/* Modal de edición */}
                        <Modal
                          isOpen={this.state.isEditModalOpen}
                          onRequestClose={this.closeEditModal}
                          contentLabel="Editar Paciente"
                        >
                          {this.state.selectedPatient && (
                            <EditPatientForm
                              patient={this.state.selectedPatient}
                              onClose={this.closeEditModal}
                            />
                          )}
                        </Modal>

                        {/* Modal de eliminación */}
                        <Modal
                          isOpen={this.state.isDeleteModalOpen}
                          onRequestClose={this.closeDeleteModal}
                          contentLabel="Eliminar Paciente"
                        >
                          {this.state.selectedPatientDelete && (
                            <DeletePatientForm
                              patient={this.state.selectedPatientDelete}
                              onCancel={this.closeDeleteModal}
                              onConfirm={this.handleConfirmDelete}
                            />
                          )}
                        </Modal>
        <button className="create-patient-button" onClick={this.handleCreatePatientClick}>Crear Paciente</button>
        {selectedPatient && <EditPatientForm patient={selectedPatient} onClose={() => this.setState({ selectedPatient: null })} />}
        {selectedPatientDelete && (<DeletePatientForm patient={selectedPatientDelete}
                              onCancel={() => this.setState({ selectedPatientDelete: null })}
                              onConfirm={this.handleConfirmDelete}/>)}



      </div>
    );
  }
}

export default PatientList;
