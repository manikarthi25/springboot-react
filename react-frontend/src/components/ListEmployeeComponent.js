import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class ListEmployeeComponent extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        }
    }

    componentDidMount() {
        EmployeeService.getEmployee().then((response) => {
            this.setState({employees : response.data});            
        })
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
                                            <td>{employee.id}</td>
                                            <td>{employee.firstName}</td>
                                            <td>{employee.lastName}</td>
                                            <td>{employee.emailId}</td>
                                        </tr>                                    
                                )
                            }
                        </tbody>

                    </table>    

                </div>
            </div>
        );
    }
}

export default ListEmployeeComponent;