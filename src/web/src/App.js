import React from "react";
import './index.css';
// eslint-disable-next-line
import {Navigate, Route, Routes} from "react-router-dom";
import UoT from "./routes/UoT";
import Home from "./routes/Home";
import PageNotFound from "./routes/PageNotFound";
import Navbar from "./components/Home/Navbar";

const App = () => {
    return (
        <div className="container">
            <Navbar/>
            <Routes>
                {/*<Route path="/" element={<Navigate to="prz"/>}/>*/}
                <Route path="/" element={<Home/>}></Route>
                <Route path="/uot" element={<UoT/>}></Route>
                <Route path="/prz" element={<Navigate to="/uot"/>}/>
                <Route path="*" element={<PageNotFound/>}/>
            </Routes>
        </div>
    );
}

export default App;