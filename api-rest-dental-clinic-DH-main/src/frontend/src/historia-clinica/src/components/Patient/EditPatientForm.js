import React, { Component } from 'react';
import './editPatientForm.css';

class EditPatientForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      lastName: '',
      dni: '',
      birthdate: '',
      gender: '',
      address: '',
      phone: '',
      email: '',
    };
  }

  componentDidMount() {
    const { id } = this.props.patient;
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('Error: No se encontró el token de autenticación.');
      return;
    }

 // Realiza una solicitud para obtener los datos del paciente
    fetch(`http://localhost:8081/patient/${id}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        const formattedDate = new Date(data.birthdate).toISOString().split('T')[0];
        // Actualiza el estado con los datos del paciente obtenidos del servidor
        this.setState({
          name: data.name,
          lastName: data.lastName,
          dni: data.dni,
          birthdate: formattedDate,
          gender: data.gender,
          address: data.adress,
          phone: data.phone,
          email: data.email,
        });
      })
      .catch((error) => {
        console.error('Error al obtener los datos del paciente:', error);
      });
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleUpdatePatient = (event) => {
    event.preventDefault();
    const { id } = this.props.patient;
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('Error: No se encontró el token de autenticación.');
      return;
    }

    // Aquí puedes enviar una solicitud HTTP al backend para editar un paciente
    const { name, lastName, dni, birthdate, gender, address, phone, email } = this.state;
    const patientData = { name, lastName, dni, birthdate, gender, adress: address, phone, email };

    fetch(`http://localhost:8081/patient/update/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify(patientData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Error al editar al paciente');
        }
        return response.json();
      })
      .then((data) => {
        console.log(data); // Maneja la respuesta exitosa del servidor aquí
        // Redirige al usuario a la página de lista de pacientes después de editar al paciente
        window.location.href = '/patient/list';
        this.updatePatientList();
      })
      .catch((error) => {
        console.error('Error al editar al paciente:', error);
      });

    this.props.onClose();
  };

  render() {
    const { name, lastName, dni, birthdate, gender, address, phone, email } = this.state;

    return (
      <div className="edit-patient-form-container">
        <h2 id="sesion">Editar Paciente</h2>
        <form onSubmit={this.handleUpdatePatient}>
          <div>
            <label htmlFor="name">Nombre:</label>
            <input
              type="text"
              id="name"
              name="name"
              value={name}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="lastName">Apellido:</label>
            <input
              type="text"
              id="lastName"
              name="lastName"
              value={lastName}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="dni">DNI:</label>
            <input
              type="text"
              id="dni"
              name="dni"
              value={dni}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="birthdate">Fecha de Nacimiento:</label>
            <input
              type="date"
              id="birthdate"
              name="birthdate"
              value={birthdate}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="gender">Sexo:</label>
            <select
              id="gender"
              name="gender"
              value={gender}
              onChange={this.handleInputChange}
              required
            >
              <option value="">Selecciona una opción</option>
              <option value="MASCULINO">Masculino</option>
              <option value="FEMENINO">Femenino</option>
            </select>
          </div>
          <div>
            <label htmlFor="address">Dirección:</label>
            <input
              type="text"
              id="address"
              name="address"
              value={address}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="phone">Teléfono:</label>
            <input
              type="text"
              id="phone"
              name="phone"
              value={phone}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="email">Correo Electrónico:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={email}
              onChange={this.handleInputChange}
              required
            />
          </div>

          <button className="edit-patient-button">Guardar Cambios</button>
          <button className="close-patient-button" onClick={this.props.onClose}>Cerrar</button>
        </form>
      </div>
    );
  }
}
export default EditPatientForm;
