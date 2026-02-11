import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import AuthProvider from './contexts/AuthContext';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './page/Home';

function App() {

  return (
    <AuthProvider>
      <BrowserRouter>
        <div className='App'>
          <Header/>
          <nav id="nav_wrap">
            <h2>EXAMPLE</h2>
          </nav>

          <main>
            <Routes>
              <Route path="/" element={<Home/>}/>
            </Routes>
          </main>
          
          <Footer/>
        </div>      
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App
