"use strict";(()=>{var A={xmlns:"http://www.w3.org/2000/svg",width:24,height:24,viewBox:"0 0 24 24",fill:"none",stroke:"currentColor","stroke-width":2,"stroke-linecap":"round","stroke-linejoin":"round"};var B=([a,e,o])=>{let r=document.createElementNS("http://www.w3.org/2000/svg",a);return Object.keys(e).forEach(t=>{r.setAttribute(t,String(e[t]))}),o?.length&&o.forEach(t=>{let s=B(t);r.appendChild(s)}),r},x=(a,e={})=>{let r={...A,...e};return B(["svg",r,a])};var i=[["path",{d:"M3.85 8.62a4 4 0 0 1 4.78-4.77 4 4 0 0 1 6.74 0 4 4 0 0 1 4.78 4.78 4 4 0 0 1 0 6.74 4 4 0 0 1-4.77 4.78 4 4 0 0 1-6.75 0 4 4 0 0 1-4.78-4.77 4 4 0 0 1 0-6.76Z"}],["path",{d:"m9 12 2 2 4-4"}]];var n=[["circle",{cx:"12",cy:"12",r:"10"}],["path",{d:"m15 9-6 6"}],["path",{d:"m9 9 6 6"}]];var g=[["circle",{cx:"12",cy:"12",r:"10"}],["path",{d:"M12 16v-4"}],["path",{d:"M12 8h.01"}]];var c=[["path",{d:"m21.73 18-8-14a2 2 0 0 0-3.48 0l-8 14A2 2 0 0 0 4 21h16a2 2 0 0 0 1.73-3"}],["path",{d:"M12 9v4"}],["path",{d:"M12 17h.01"}]];var w=[["path",{d:"M18 6 6 18"}],["path",{d:"m6 6 12 12"}]];var C=document.querySelector(".toast-container");C||(C=document.createElement("div"),C.className=".toast-container",document.body.appendChild(C));function L(a){let e=document.querySelector(`.toast-container.${a}`);return e||(e=document.createElement("div"),e.className=`toast-container ${a}`,document.body.appendChild(e)),e}function y(a="default"){switch(a){case"success":return i;case"error":return n;case"warning":return c;case"default":default:return g}}function R(a="default"){let e=y(a);return x(e,{size:20})}function M({message:a,type:e="default",duration:o=5e3,position:r="bottom-right",theme:t="dark"}){let s=L(r),f=document.createElement("div");f.className=`toast ${e} ${t}`,s.appendChild(f);let d=R(e);d.classList.add("toast-icon"),f.appendChild(d);let u=document.createElement("span");u.className="toast-message",u.textContent=a,f.appendChild(u);let h=x(w,{size:16});h.classList.add("toast-close-icon");let l=document.createElement("button");l.className="toast-close",l.classList.add("toast-close"),l.appendChild(h),f.appendChild(l);let p,m,D=o,k=()=>{f.remove()},P=()=>{m=Date.now(),p=window.setTimeout(k,o)},F=()=>{S();let T=Date.now()-m;D-=T},S=()=>{clearTimeout(p)};P(),l.onclick=()=>{S(),k()},f.addEventListener("mouseenter",F),f.addEventListener("mouseleave",()=>{S(),P()})}document.addEventListener("DOMContentLoaded",()=>{document.querySelectorAll(".toast-auto").forEach(e=>{let o=e.dataset.message;if(!o)return;let r=["success","error","warning","default"],t=e.dataset.type,s=r.includes(t)?t:"default",f=["top-left","top-center","top-right","bottom-left","bottom-center","bottom-right"],d=e.dataset.position,u=r.includes(t)?d:"bottom-left",h=["light","dark"],l=e.dataset.theme,p=r.includes(t)?l:"dark",m=e.dataset.duration?parseInt(e.dataset.duration):5e3;M({message:o,type:s,position:u,theme:p,duration:m})})});window.showToast=M;})();
/*! Bundled license information:

lucide/dist/esm/defaultAttributes.js:
lucide/dist/esm/createElement.js:
lucide/dist/esm/icons/badge-check.js:
lucide/dist/esm/icons/circle-x.js:
lucide/dist/esm/icons/info.js:
lucide/dist/esm/icons/triangle-alert.js:
lucide/dist/esm/icons/x.js:
lucide/dist/esm/lucide.js:
  (**
   * @license lucide v0.536.0 - ISC
   *
   * This source code is licensed under the ISC license.
   * See the LICENSE file in the root directory of this source tree.
   *)
*/
