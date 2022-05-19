import React, { Component } from 'react';

class HeaderComponent extends Component {

    constructor(props) {
        
        super(props);
        this.state = {

        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav className = 'navbar navbar-expand-md navbar-dark bg-dark'>
                        <div>
                            <a href = "http://localhost:3000" className='navbar-brand'>Home</a>
                            <a href = "http://localhost:3000/employees" className='navbar-brand'>Employees Details</a>
                            <a href = "http://localhost:3000/add-employee" className='navbar-brand'>Add Employee</a>
                            <a href = "http://localhost:3000/update-employee" className='navbar-brand'>Update Employee</a>
                            <a href = "http://localhost:3000/navigate" className='navbar-brand'>Test Navigate</a>

                        </div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;
