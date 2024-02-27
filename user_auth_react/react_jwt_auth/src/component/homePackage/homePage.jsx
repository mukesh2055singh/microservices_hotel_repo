import React, { useState,useEffect } from 'react';
import { About }  from './about'
import { Header }  from './header';
import { Features }  from './features';
import { Services }  from './services';
import { Gallery }  from './gallery';
import { Testimonials }  from './testimonials';
import { Team }  from './Team';
import { Contact }  from './contact';
import { Navigation }  from './navigation';
import JsonData from "../../data/data.json";

const HomePage = () =>{
    const [landingPageData, setLandingPageData] = useState({});
    useEffect(() => {
    setLandingPageData(JsonData);
  }, []);
  return (
      <div>
        <Navigation />
        <Header data={landingPageData.Header} />
        <Contact data={landingPageData.Contact} />
        <About data={landingPageData.About} />
        <Services data={landingPageData.Services} />
        <Gallery data={landingPageData.Gallery} />
        <Testimonials data={landingPageData.Testimonials} />
        <Team data={landingPageData.Team} />
        <Features data={landingPageData.Features} />
        <Contact data={landingPageData.Contact} />
      </div>
  );
}
export default HomePage;