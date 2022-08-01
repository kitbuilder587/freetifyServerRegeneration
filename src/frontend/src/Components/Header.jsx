import React from "react";

const Header = (props) => {
    return(
        <div className="Header">

            <div className="User-logo">
                <img src={props.user.logo} alt="" width="100px" height="100px"/>
                <div className="User-id">
                    Id: {props.user.id}
                </div>
            </div>

            <div className="User-card">
                <div className="Username">
                    <h2>{props.user.username}</h2>
                </div>
                <div className="User-description">
                    {props.user.description}
                </div>
            </div>

            <button className="Upload-new-track-button">
                Upload track
            </button>

            <div className='Log-div'>
                <label className="Log-label">
                    
                </label>
            </div>
        </div>
    );
};

export default Header;