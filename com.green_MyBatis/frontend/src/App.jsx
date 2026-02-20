import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter,Routes,Route } from 'react-router-dom';
import AuthProvider from './contexts/AuthContext';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './page/Home';
import Signup from './page/Signup';
import SignupResult from './page/SignupResult';
import Login from './page/Login';
import MyInfo from './page/Myinfo';
import Modify from './page/Modify';
import Product from './page/Product';


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
              <Route path="/member/signup" element={<Signup/>}/>
              <Route path="/member/signup_result" element={<SignupResult/>}/>
              <Route path="/member/login" element={<Login/>}/>
              <Route path="/member/myinfo" element={<MyInfo/>}/>
              <Route path="/member/modify" element={<Modify/>}/>
              <Route path="/cars/insert" element={<Product/>}/>
            </Routes>
          </main>
          
          <Footer/>
        </div>      
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App
