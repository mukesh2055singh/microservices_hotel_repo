import React from "react";
import { BrowserRouter as Router,Link,Routes,Route } from "react-router-dom";
import ShowLoginPage   from "../loginPackage/showLoginPage";

export const Navigation = (props) => {
  return (
   
    <nav id="menu" className="navbar navbar-default navbar-fixed-top">
      <div className="container">
        <div className="navbar-header">
          <button
            type="button"
            className="navbar-toggle collapsed"
            data-toggle="collapse"
            data-target="#bs-example-navbar-collapse-1"
          >
            {" "}
            <span className="sr-only">Toggle navigation</span>{" "}
            <span className="icon-bar"></span>{" "}
            <span className="icon-bar"></span>{" "}
            <span className="icon-bar"></span>{" "}
          </button>
          <a className="navbar-brand page-scroll" href="#page-top">
            React Landing Page
          </a>{" "}
        </div>

        <div
          className="collapse navbar-collapse"
          id="bs-example-navbar-collapse-1"
        >
          <ul className="nav navbar-nav navbar-right">
            <li>
              <Link to="/detail/features">FEATURES</Link>
            </li>
            <li>
              <Link to="/detail/about">ABOUT</Link>
            </li>
            <li>
              <Link to="/detail/services">SERVICES</Link>
            </li>
            <li>
            <Link to="/detail/gallery">GALLERY</Link>
            </li>
            <li>
              <Link to="/detail/testimonial">TESTIMONIALS</Link>
            </li>
            <li>
              <Link to="/detail/team">TEAM</Link>
            </li>
            <li>
              <Link to="/detail/contact">CONTACT</Link>
            </li>
            <li>
              <Link to="/user/showLogin">LOGIN</Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>  
  );
};
