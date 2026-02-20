import{Link} from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';
import { useContext } from 'react';
import './Header.css';

export default function Header(){
    //전역 저장소에서 user와 logout 함수 직접 가져온다.
    const {user, logout} = useContext(AuthContext);

    return(
        <header>
            <div id="top">
                <h3>MEMBER JOIN</h3>
            </div>
            <div id="header_wrap">
                <Link to="/">HOME</Link>
            </div>
            <div style={{textAlign:'center'}}>
            {!user ? (
               <>
                    <Link to="/member/signup" style={{padding:'15px'}} >회원가입</Link>
                    <Link to="/member/login" style={{padding:'15px'}}>로그인</Link>
               </> 
            ):(
                <>
                    <span style={{fontWeight:'bold',color:'#333'}}>
                        {user === 'admin9867' ? 
                        <>
                            <span>관리자</span>
                            <Link to="/member/list" style={{padding:'15px'}}>[회원목록]</Link>
                            <Link to="/cars/insert" style={{padding:'15px'}}>[상품등록]</Link>
                        </>
                        : <span style={{padding:'15px'}}>{user}님 환영</span>                        
                        }
                    </span>
                        
                    {/* logout함수 연결부분 */}
                    <Link to="/" onClick={logout} style={{padding:'15px'}}>로그아웃</Link>
                    <Link to="/member/myinfo" style={{padding:'15px'}}>개인상세정보</Link>
                </>
            )}
            <Link to="/board/list" style={{padding:'15px'}}>게시판</Link>
            </div>
        </header>
    )

}