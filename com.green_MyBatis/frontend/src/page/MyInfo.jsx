import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../contexts/AuthContext';
import { useContext } from 'react';

export default function MyInfo() {
  
  const[member,setMember] = useState(null);
  //회원탈퇴시 => 로그인 상태에서 벗어나야 하므로 사용
  const{logout} = useContext(AuthContext);
  const navigate = useNavigate();

  //내 정보조회
  useEffect(()=>{
    axios.get("/api/member/myinfo") 
    .then((res)=>{
      if(res.data){
          //불러오기 성공
          setMember(res.data);
      }else{
          //불러오기 실패
          alert("로그인이 필요합니다.");
          navigate("/member/login");
      }
      })
      .catch((error)=>{
          console.error(error);        
      })
  },[])

  //회원삭제 핸들러
  const deleteHandler = () =>{
    if(!window.confirm("정말 삭제하시겠습니까? 삭제시 영구 복구 불가능합니다.")){
      return;
    }
    
    axios.delete('/api/member/delete')
    .then((res)=>{
      if(res.data === 1){
        alert("회원이 삭제되었습니다");
        logout();
        navigate("/");
      }else{
        alert("삭제 실패");
      }
    })
    .catch((error)=>console.log(error));
  }

  //지금현재 member=null이므로 개인정보 출력이 안됨. 이에대한 예외처리
   if(!member){
    return <div>로딩중...</div>
   }

  return (
    <section>
      <div id="section_wrap">
        <div className="word">
          <h2>개인 회원 상세 정보</h2>
        </div>

        <div className="content">
          <table border="1">
            <tbody>
              <tr>
                <th>아이디</th>
                <td>{member.id}</td>
              </tr>
              <tr>
                <th>이메일</th>
                <td>{member.mail}</td>
              </tr>
              <tr>
                <th>전화</th>
                <td>{member.phone}</td>
              </tr>
              <tr>
                <th>등록일</th>
                <td>{member.reg_date}</td>
              </tr>
            </tbody>
          </table>

          {/* 버튼 영역 */}
          <div className="btn-area" style={{ marginTop: '20px' }}>
            <button className="btn" onClick={() => navigate('/member/modify')}>
              회원수정
            </button>

            <button className="btn btn-danger" onClick={deleteHandler} >
              회원탈퇴
            </button>

            <button className="btn" onClick={() => navigate('/')}>
              홈으로
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}