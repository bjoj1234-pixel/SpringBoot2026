import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

export default function Modify() {
  

  return (
    <section>
      <div id="section_wrap">
        <div className="word">
          <h2>개인 회원 정보 수정</h2>
        </div>

        <div className="content">
          <table className="detail-table">
            <tbody>
              <tr>
                <th>아이디</th>
                <td>
                  <input type="text" name="id" />
                </td>
              </tr>

              <tr>
                <th>이메일</th>
                <td>
                  <input
                    type="text"
                    name="mail"
                  />
                </td>
              </tr>

              <tr>
                <th>전화</th>
                <td>
                  <input
                    type="text"
                    name="phone"
                  />
                </td>
              </tr>

              <tr>
                <th>패스워드 확인</th>
                <td>
                  <input
                    type="password"
                    name="pw"
                  />
                </td>
              </tr>
            </tbody>
          </table>

          <div className="btn-area" style={{ marginTop: '20px' }}>
            <button className="btn" onClick={handleSubmit}>
              회원 수정하기
            </button>

            <button className="btn" onClick={() => navigate('/member/myinfo')}>
              취소
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}