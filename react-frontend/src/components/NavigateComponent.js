import {useNavigate} from'react-router-dom';

function NavigateComponent() {

    const navigate = useNavigate();

    return(
        <div>
            <div className='"container'>
                <button className='btn btn-primary' onClick={() => { navigate("/add-employee");}}> Add Employee  </button>
            </div>            
        </div>
    );
}

export default NavigateComponent;