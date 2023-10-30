import React, { Component } from 'react';
import './createPatient.css'; // Asegúrate de tener el archivo CSS adecuado

class CreatePatient extends Component {
  constructor() {
    super();
    this.state = {
      name: '',
      lastName: '',
      dni: '',
      birthdate: '',
      gender: '',
      address: '',
      phone: '',
      email: ''
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleCreatePatient = (event) => {
    event.preventDefault();
    const token = localStorage.getItem('token');
    if (!token) {
      console.error('Error: No se encontró el token de autenticación.');
      return;
    }

    const { name, lastName, dni, birthdate, gender, address, phone, email } = this.state;
    const patientData = { name, lastName, dni, birthdate, gender, adress: address, phone, email };

    fetch('http://localhost:8081/patient/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(patientData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Error al crear al paciente');
        }
        return response.json();
      })
      .then((data) => {
        console.log(data); // Maneja la respuesta exitosa del servidor aquí
        // Redirige al usuario a la página de lista de pacientes después de crear al paciente
        window.location.href = '/patient/list';
      })
      .catch((error) => {
        console.error('Error al crear al paciente:', error);
      });
  };

  render() {
    return (
      <div className="create-patient-container">
        <h2>Crear Nuevo Paciente</h2>
        <form onSubmit={this.handleCreatePatient}>
          <div>
            <label htmlFor="name">Nombre:</label>
            <input
              type="text"
              id="name"
              name="name"
              value={this.state.name}
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
              value={this.state.lastName}
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
              value={this.state.dni}
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
              value={this.state.birthdate}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div>
            <label htmlFor="gender">Sexo:</label>
            <select
              id="gender"
              name="gender"
              value={this.state.gender}
              onChange={this.handleInputChange}
              required
            >
              <option value="">Selecciona una opción</option>
              <option value="MASCULINO">Masculino</option>
              <option value="FEMENINO">Femenino</option>
              <option value="NO ESPECIFICAR">NO Especificar</option>

            </select>
          </div>
          <div>
            <label htmlFor="address">Dirección:</label>
            <input
              type="text"
              id="address"
              name="address"
              value={this.state.address}
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
              value={this.state.phone}
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
              value={this.state.email}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <button className="create-patient-button">Crear Paciente</button>
        </form>
      </div>
    );
  }
}

export default CreatePatient;
