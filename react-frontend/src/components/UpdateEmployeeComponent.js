import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class UpdateEmployeeComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: 1,
            firstName: '',
            lastName: '',
            emailId:''
        }

        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEamilIdHandler = this.changeEamilIdHandler.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
    }

    componentDidMount() {
        EmployeeService.getEmployeeById(1).then((response => {
        let employee = response.data;
        this.setState({firstName:employee.firstName, lastName:employee.lastName, emailId:employee.emailId});
        console.log("Component DId Mount =>" + JSON.stringify(employee));

        }));
    }

    updateEmployee = (e) => {
        e.preventDefault();
        let employee = {firstName: this.state.firstName, lastName:this.state.lastName, emailId:this.state.emailId };
        console.log("employee =>" + JSON.stringify(employee));
    }  
    
    cancel = (e) => {
        e.preventDefault();
        this.props.history.push('/employees')
    }
    
    changeFirstNameHandler = (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastName: event.target.value});
    }

    changeEamilIdHandler = (event) => {
        this.setState({emailId : event.target.value});
    }
    
    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className='card col-md-6 offset-md-3 offset-md-3'>
                            <h3 className='text-center'>Update Employee</h3>
                            <div className='card-body'>
                                <form>
                                    <div className='form-group'>
                                        <label>First Name</label>
                                        <input placeholder='First Name' name="firstName" className='form-control'
                                         value={this.state.firstName} onChange={this.changeFirstNameHandler}></input>
                                    </div>
                                    <div className='form-group'>
                                        <label>Last Name</label>
                                        <input placeholder='Last Name' name="lastName" className='form-control'
                                         value={this.state.lastName} onChange={this.changeLastNameHandler}></input>
                                    </div>
                                    <div className='form-group'>
                                        <label>Email Id</label>
                                        <input placeholder='Email Id' name="emailId" className='form-control'
                                         value={this.state.emailId} onChange={this.changeEamilIdHandler}></input>
                                    </div>
                                    <button className='btn btn-primary' onClick={this.updateEmployee}>Save</button>
                                    <button className='btn btn-danger' onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UpdateEmployeeComponent;