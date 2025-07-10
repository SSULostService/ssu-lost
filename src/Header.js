import React from "react";

function Header(){
    return(
        <header className="site-header">
            <div className="header-content">
                <h1 id="HeadLine">SSU LOST</h1>
            </div>
            <div className="line"></div>
            <button className="map">지도로 보기</button>
        </header>
    );
}

export default Header;