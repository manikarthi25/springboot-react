import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class ListEmployeeComponent extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        }
        this.addEmployee = this.addEmployee.bind(this);
        this.updateEmployee = this.updateEmployee.bind(this);
    }

    componentDidMount() {
        EmployeeService.getEmployee().then((response) => {
            this.setState({employees : response.data});            
        })
    }

    addEmployee() {
        this.props.history.push('/add-employee');
    }

    updateEmployee(id) {
        this.props.history.push(`/update-employee/${id}`);
    }

    render() {
        return (
            <div>
                <h2 className = "text-center">Employees List</h2>
                
                <div className="row">
                    <table className="table table-striped table-bordered text-center">

                        <thead>
                            <tr>
                                <th>First-Name</th>
                                <th>Last-Name</th>
                                <th>Email-Id</th>
                                <th>Actions</th>
                            </tr>                            
                        </thead>

                        <tbody>
                            {
                                this.state.employees.map(
                                    employee => 
                                        <tr key = {employee.id}>
                                            <td>{employee.firstName}</td>
                                            <td>{employee.lastName}</td>
                                            <td>{employee.emailId}</td>
                                            <td>
                                                <button className='btn btn-info' onClick={() => this.updateEmployee(employee.id)}>Update</button>
                                            </td>
                                        </tr>                                    
                                )
                            }
                        </tbody>

                    </table>    

                </div>
                <div>
                    <button className='btn btn-primary' onClick={this.addEmployee}>Add Employee</button>
                </div>
            </div>
        );
    }
}

export default ListEmployeeComponent;