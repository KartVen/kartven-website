import './HeroImage.css';
import React from "react";
import HeroJPG from "../../assets/desktop-web-development.jpg";


const HeroImage = () => {
    return (
        <div className="hero hero-box">
            <div className="mask">
                <img src={HeroJPG} alt={document.title}/>
            </div>
            <div className="cnt">
                <div>
                    <h1 className="cnt-lg">Witma. - GD</h1>
                </div>
                <div>
                    <h2 className="cnt-lg">Platforma działa poprawnie.</h2>
                </div>
            </div>
        </div>
    );
}

export default HeroImage;