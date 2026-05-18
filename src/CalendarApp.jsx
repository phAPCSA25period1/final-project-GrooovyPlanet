import { useState, useEffect } from "react";

const MONTHS = ["January","February","March","April","May","June","July","August","September","October","November","December"];
const DAYS = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];

const styles = `
  @import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;700;900&family=DM+Mono:wght@300;400;500&display=swap');

  *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

  :root {
    --ink: #1a1208;
    --paper: #f5f0e8;
    --cream: #ede8dc;
    --accent: #c0392b;
    --accent-light: #e8d5d3;
    --gold: #b8860b;
    --muted: #7a6f60;
    --border: #c8bfaf;
    --event-dot: #c0392b;
  }

  body { background: var(--paper); color: var(--ink); font-family: 'DM Mono', monospace; min-height: 100vh; }

  .app {
    max-width: 1100px;
    margin: 0 auto;
    padding: 32px 20px;
    display: grid;
    grid-template-columns: 1fr 340px;
    grid-template-rows: auto 1fr;
    gap: 0;
    min-height: 100vh;
  }

  /* HEADER */
  .header {
    grid-column: 1 / -1;
    border-bottom: 3px double var(--ink);
    padding-bottom: 16px;
    margin-bottom: 28px;
    display: flex;
    align-items: baseline;
    gap: 20px;
  }
  .header h1 {
    font-family: 'Playfair Display', serif;
    font-size: 2.8rem;
    font-weight: 900;
    letter-spacing: -1px;
    line-height: 1;
  }
  .header .subtitle {
    font-size: 0.7rem;
    color: var(--muted);
    text-transform: uppercase;
    letter-spacing: 3px;
    margin-bottom: 4px;
  }

  /* CALENDAR PANEL */
  .calendar-panel {
    padding-right: 32px;
    border-right: 1px solid var(--border);
  }

  .cal-nav {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  .cal-nav h2 {
    font-family: 'Playfair Display', serif;
    font-size: 1.6rem;
    font-weight: 700;
    letter-spacing: -0.5px;
  }
  .cal-nav button {
    background: none;
    border: 1px solid var(--border);
    color: var(--ink);
    width: 32px;
    height: 32px;
    cursor: pointer;
    font-size: 1rem;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.15s;
  }
  .cal-nav button:hover { background: var(--ink); color: var(--paper); }

  .cal-grid {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    gap: 1px;
    background: var(--border);
    border: 1px solid var(--border);
  }
  .cal-day-header {
    background: var(--ink);
    color: var(--paper);
    text-align: center;
    padding: 8px 4px;
    font-size: 0.65rem;
    letter-spacing: 2px;
    text-transform: uppercase;
  }
  .cal-cell {
    background: var(--paper);
    min-height: 80px;
    padding: 6px;
    cursor: pointer;
    transition: background 0.12s;
    position: relative;
  }
  .cal-cell:hover { background: var(--cream); }
  .cal-cell.other-month { background: var(--cream); opacity: 0.5; cursor: default; }
  .cal-cell.today { outline: 2px solid var(--accent); outline-offset: -2px; }
  .cal-cell.selected { background: var(--accent-light); }
  .cal-date {
    font-size: 0.75rem;
    font-weight: 500;
    color: var(--muted);
    margin-bottom: 4px;
  }
  .cal-cell.today .cal-date { color: var(--accent); font-weight: 700; }
  .event-pill {
    background: var(--accent);
    color: white;
    font-size: 0.58rem;
    padding: 2px 5px;
    margin-bottom: 2px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    cursor: pointer;
    transition: opacity 0.12s;
  }
  .event-pill:hover { opacity: 0.8; }
  .more-events { font-size: 0.58rem; color: var(--muted); }

  /* SIDEBAR */
  .sidebar {
    padding-left: 28px;
  }
  .sidebar-section {
    margin-bottom: 28px;
  }
  .sidebar-section h3 {
    font-family: 'Playfair Display', serif;
    font-size: 1rem;
    font-weight: 700;
    border-bottom: 1px solid var(--border);
    padding-bottom: 6px;
    margin-bottom: 14px;
    display: flex;
    align-items: center;
    gap: 8px;
  }
  .sidebar-section h3 .badge {
    background: var(--accent);
    color: white;
    font-family: 'DM Mono', monospace;
    font-size: 0.65rem;
    padding: 1px 6px;
    font-weight: 400;
  }

  /* FORM */
  .form-row { margin-bottom: 10px; }
  .form-row label { display: block; font-size: 0.65rem; text-transform: uppercase; letter-spacing: 2px; color: var(--muted); margin-bottom: 4px; }
  .form-row input {
    width: 100%;
    background: var(--cream);
    border: 1px solid var(--border);
    border-radius: 0;
    padding: 8px 10px;
    font-family: 'DM Mono', monospace;
    font-size: 0.78rem;
    color: var(--ink);
    outline: none;
    transition: border-color 0.15s;
  }
  .form-row input:focus { border-color: var(--ink); }
  .btn {
    display: inline-block;
    padding: 8px 18px;
    font-family: 'DM Mono', monospace;
    font-size: 0.72rem;
    text-transform: uppercase;
    letter-spacing: 2px;
    cursor: pointer;
    border: none;
    transition: all 0.15s;
  }
  .btn-primary { background: var(--ink); color: var(--paper); }
  .btn-primary:hover { background: var(--accent); }
  .btn-danger { background: none; color: var(--accent); border: 1px solid var(--accent); padding: 4px 8px; font-size: 0.65rem; }
  .btn-danger:hover { background: var(--accent); color: white; }
  .btn-sm { padding: 4px 10px; font-size: 0.65rem; }

  /* EVENT LIST */
  .event-item {
    display: flex;
    align-items: flex-start;
    gap: 10px;
    padding: 8px 0;
    border-bottom: 1px solid var(--cream);
    animation: fadeIn 0.2s ease;
  }
  .event-item:last-child { border-bottom: none; }
  @keyframes fadeIn { from { opacity: 0; transform: translateY(-4px); } to { opacity: 1; transform: translateY(0); } }
  .event-dot { width: 8px; height: 8px; background: var(--accent); margin-top: 5px; flex-shrink: 0; }
  .event-info { flex: 1; min-width: 0; }
  .event-title { font-size: 0.78rem; font-weight: 500; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
  .event-date { font-size: 0.65rem; color: var(--muted); margin-top: 1px; }

  /* SEARCH */
  .search-results { margin-top: 10px; }
  .no-events { font-size: 0.72rem; color: var(--muted); font-style: italic; padding: 8px 0; }

  /* MODAL */
  .modal-overlay {
    position: fixed; inset: 0;
    background: rgba(26,18,8,0.6);
    display: flex; align-items: center; justify-content: center;
    z-index: 1000;
    animation: fadeIn 0.15s ease;
  }
  .modal {
    background: var(--paper);
    border: 1px solid var(--border);
    padding: 28px;
    width: 380px;
    max-width: 95vw;
    position: relative;
  }
  .modal h3 {
    font-family: 'Playfair Display', serif;
    font-size: 1.2rem;
    margin-bottom: 16px;
    padding-bottom: 10px;
    border-bottom: 1px solid var(--border);
  }
  .modal-close {
    position: absolute; top: 12px; right: 12px;
    background: none; border: none; font-size: 1.2rem;
    cursor: pointer; color: var(--muted);
  }
  .modal-actions { display: flex; gap: 8px; margin-top: 16px; justify-content: flex-end; }

  /* TOAST */
  .toast {
    position: fixed; bottom: 24px; left: 50%; transform: translateX(-50%);
    background: var(--ink); color: var(--paper);
    font-family: 'DM Mono', monospace;
    font-size: 0.72rem;
    padding: 10px 20px;
    letter-spacing: 1px;
    z-index: 2000;
    animation: slideUp 0.2s ease;
  }
  @keyframes slideUp { from { opacity: 0; transform: translateX(-50%) translateY(10px); } to { opacity: 1; transform: translateX(-50%) translateY(0); } }

  .tag { font-size: 0.6rem; text-transform: uppercase; letter-spacing: 2px; color: var(--muted); }
  .divider { border: none; border-top: 1px solid var(--border); margin: 16px 0; }
  .tab-row { display: flex; gap: 0; margin-bottom: 16px; }
  .tab { padding: 6px 14px; font-family: 'DM Mono', monospace; font-size: 0.65rem; text-transform: uppercase; letter-spacing: 1px; cursor: pointer; border: 1px solid var(--border); background: none; color: var(--muted); transition: all 0.12s; }
  .tab.active { background: var(--ink); color: var(--paper); border-color: var(--ink); }
  .tab:not(:last-child) { border-right: none; }

  @media (max-width: 700px) {
    .app { grid-template-columns: 1fr; grid-template-rows: auto auto auto; padding: 16px; }
    .calendar-panel { padding-right: 0; border-right: none; border-bottom: 1px solid var(--border); padding-bottom: 24px; margin-bottom: 24px; }
    .sidebar { padding-left: 0; }
  }
`;

function getDaysInMonth(year, month) {
  return new Date(year, month + 1, 0).getDate();
}
function getFirstDayOfMonth(year, month) {
  return new Date(year, month, 1).getDay();
}
function todayStr() {
  return new Date().toISOString().slice(0, 10);
}
function formatDateDisplay(d) {
  const [y, m, day] = d.split("-");
  return `${MONTHS[parseInt(m) - 1]} ${parseInt(day)}, ${y}`;
}

export default function CalendarApp() {
  const today = new Date();
  const [viewYear, setViewYear] = useState(today.getFullYear());
  const [viewMonth, setViewMonth] = useState(today.getMonth());
  const [selectedDate, setSelectedDate] = useState(todayStr());
  const [events, setEvents] = useState([
    { title: "Math Final", date: `${today.getFullYear()}-${String(today.getMonth()+1).padStart(2,"0")}-${String(today.getDate()).padStart(2,"0")}` },
    { title: "Science Project", date: `${today.getFullYear()}-${String(today.getMonth()+1).padStart(2,"0")}-${String(Math.min(today.getDate()+3,28)).padStart(2,"0")}` },
  ]);
  const [modal, setModal] = useState(null); // null | "add" | "edit"
  const [editTarget, setEditTarget] = useState(null);
  const [form, setForm] = useState({ title: "", date: "" });
  const [searchQuery, setSearchQuery] = useState("");
  const [activeTab, setActiveTab] = useState("day"); // "day" | "search" | "all"
  const [toast, setToast] = useState(null);

  useEffect(() => {
    if (toast) {
      const t = setTimeout(() => setToast(null), 2200);
      return () => clearTimeout(t);
    }
  }, [toast]);

  function showToast(msg) { setToast(msg); }

  // Build calendar grid
  const firstDay = getFirstDayOfMonth(viewYear, viewMonth);
  const daysInMonth = getDaysInMonth(viewYear, viewMonth);
  const prevMonthDays = getDaysInMonth(viewYear, viewMonth - 1 < 0 ? 11 : viewMonth - 1);
  const cells = [];
  for (let i = firstDay - 1; i >= 0; i--) {
    const d = prevMonthDays - i;
    const m = viewMonth - 1 < 0 ? 11 : viewMonth - 1;
    const y = viewMonth - 1 < 0 ? viewYear - 1 : viewYear;
    cells.push({ day: d, month: m, year: y, other: true });
  }
  for (let d = 1; d <= daysInMonth; d++) {
    cells.push({ day: d, month: viewMonth, year: viewYear, other: false });
  }
  const remaining = 42 - cells.length;
  for (let d = 1; d <= remaining; d++) {
    const m = viewMonth + 1 > 11 ? 0 : viewMonth + 1;
    const y = viewMonth + 1 > 11 ? viewYear + 1 : viewYear;
    cells.push({ day: d, month: m, year: y, other: true });
  }

  function cellDateStr(cell) {
    return `${cell.year}-${String(cell.month + 1).padStart(2, "0")}-${String(cell.day).padStart(2, "0")}`;
  }

  function eventsOnDate(dateStr) {
    return events.filter(e => e.date === dateStr);
  }

  function openAdd(date) {
    setForm({ title: "", date: date || selectedDate });
    setModal("add");
  }

  function openEdit(ev) {
    setEditTarget(ev);
    setForm({ title: ev.title, date: ev.date });
    setModal("edit");
  }

  function saveAdd() {
    if (!form.title.trim()) return showToast("Title is required.");
    if (!form.date) return showToast("Date is required.");
    const dup = events.find(e => e.title.toLowerCase() === form.title.trim().toLowerCase() && e.date === form.date);
    if (dup) return showToast("Duplicate event detected.");
    setEvents(prev => [...prev, { title: form.title.trim(), date: form.date }]);
    setModal(null);
    showToast("Event added.");
  }

  function saveEdit() {
    if (!form.title.trim()) return showToast("Title is required.");
    if (!form.date) return showToast("Date is required.");
    setEvents(prev => prev.map(e =>
      e === editTarget ? { title: form.title.trim(), date: form.date } : e
    ));
    setModal(null);
    showToast("Event updated.");
  }

  function removeEvent(ev) {
    setEvents(prev => prev.filter(e => e !== ev));
    showToast("Event removed.");
  }

  function removeByTitle(title) {
    const count = events.filter(e => e.title.toLowerCase() === title.toLowerCase()).length;
    setEvents(prev => prev.filter(e => e.title.toLowerCase() !== title.toLowerCase()));
    showToast(`Removed ${count} event(s) titled "${title}".`);
  }

  const todayEvents = eventsOnDate(selectedDate);
  const searchResults = searchQuery.trim()
    ? events.filter(e =>
        e.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
        e.date.includes(searchQuery)
      ).sort((a,b) => a.date.localeCompare(b.date))
    : [];
  const sortedAll = [...events].sort((a,b) => a.date.localeCompare(b.date));

  return (
    <>
      <style>{styles}</style>
      <div className="app">
        {/* HEADER */}
        <header className="header">
          <div>
            <div className="subtitle">Event Planner</div>
            <h1>The Calendar</h1>
          </div>
          <div style={{ marginLeft: "auto", textAlign: "right" }}>
            <div className="tag">Total events</div>
            <div style={{ fontFamily: "'Playfair Display', serif", fontSize: "2rem", fontWeight: 900, color: "var(--accent)" }}>
              {events.length}
            </div>
          </div>
        </header>

        {/* CALENDAR */}
        <main className="calendar-panel">
          <div className="cal-nav">
            <button onClick={() => {
              if (viewMonth === 0) { setViewMonth(11); setViewYear(y => y - 1); }
              else setViewMonth(m => m - 1);
            }}>‹</button>
            <h2>{MONTHS[viewMonth]} {viewYear}</h2>
            <button onClick={() => {
              if (viewMonth === 11) { setViewMonth(0); setViewYear(y => y + 1); }
              else setViewMonth(m => m + 1);
            }}>›</button>
          </div>
          <div className="cal-grid">
            {DAYS.map(d => <div key={d} className="cal-day-header">{d}</div>)}
            {cells.map((cell, i) => {
              const ds = cellDateStr(cell);
              const dayEvents = eventsOnDate(ds);
              const isToday = ds === todayStr();
              const isSel = ds === selectedDate;
              return (
                <div
                  key={i}
                  className={`cal-cell${cell.other ? " other-month" : ""}${isToday ? " today" : ""}${isSel && !cell.other ? " selected" : ""}`}
                  onClick={() => { if (!cell.other) { setSelectedDate(ds); setActiveTab("day"); } }}
                >
                  <div className="cal-date">{cell.day}</div>
                  {dayEvents.slice(0, 2).map((ev, j) => (
                    <div key={j} className="event-pill" title={ev.title}
                      onClick={e => { e.stopPropagation(); openEdit(ev); }}
                    >{ev.title}</div>
                  ))}
                  {dayEvents.length > 2 && <div className="more-events">+{dayEvents.length - 2} more</div>}
                </div>
              );
            })}
          </div>
          <div style={{ marginTop: 16, display: "flex", justifyContent: "flex-end" }}>
            <button className="btn btn-primary" onClick={() => openAdd(selectedDate)}>+ Add Event</button>
          </div>
        </main>

        {/* SIDEBAR */}
        <aside className="sidebar">
          <div className="tab-row">
            <button className={`tab${activeTab==="day"?" active":""}`} onClick={() => setActiveTab("day")}>Day</button>
            <button className={`tab${activeTab==="search"?" active":""}`} onClick={() => setActiveTab("search")}>Search</button>
            <button className={`tab${activeTab==="all"?" active":""}`} onClick={() => setActiveTab("all")}>All</button>
          </div>

          {/* DAY VIEW */}
          {activeTab === "day" && (
            <div className="sidebar-section">
              <h3>
                {formatDateDisplay(selectedDate)}
                <span className="badge">{todayEvents.length}</span>
              </h3>
              {todayEvents.length === 0
                ? <div className="no-events">No events. Click a day or add one.</div>
                : todayEvents.map((ev, i) => (
                  <div key={i} className="event-item">
                    <div className="event-dot" />
                    <div className="event-info">
                      <div className="event-title">{ev.title}</div>
                      <div className="event-date">{formatDateDisplay(ev.date)}</div>
                    </div>
                    <button className="btn btn-danger" onClick={() => openEdit(ev)}>Edit</button>
                    <button className="btn btn-danger" onClick={() => removeEvent(ev)}>✕</button>
                  </div>
                ))
              }
              <button className="btn btn-primary btn-sm" style={{ marginTop: 12 }} onClick={() => openAdd(selectedDate)}>
                + Add to this day
              </button>
            </div>
          )}

          {/* SEARCH */}
          {activeTab === "search" && (
            <div className="sidebar-section">
              <h3>Search Events</h3>
              <div className="form-row">
                <label>Title, date, or month (YYYY-MM)</label>
                <input
                  value={searchQuery}
                  onChange={e => setSearchQuery(e.target.value)}
                  placeholder="e.g. Math or 2025-06"
                  autoFocus
                />
              </div>
              <div className="search-results">
                {searchQuery.trim() && searchResults.length === 0 && (
                  <div className="no-events">No matching events.</div>
                )}
                {searchResults.map((ev, i) => (
                  <div key={i} className="event-item">
                    <div className="event-dot" />
                    <div className="event-info">
                      <div className="event-title">{ev.title}</div>
                      <div className="event-date">{formatDateDisplay(ev.date)}</div>
                    </div>
                    <button className="btn btn-danger" onClick={() => openEdit(ev)}>Edit</button>
                    <button className="btn btn-danger" onClick={() => removeEvent(ev)}>✕</button>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* ALL EVENTS */}
          {activeTab === "all" && (
            <div className="sidebar-section">
              <h3>
                All Events
                <span className="badge">{events.length}</span>
              </h3>
              {sortedAll.length === 0 && <div className="no-events">No events yet.</div>}
              {sortedAll.map((ev, i) => (
                <div key={i} className="event-item">
                  <div className="event-dot" />
                  <div className="event-info">
                    <div className="event-title">{ev.title}</div>
                    <div className="event-date">{formatDateDisplay(ev.date)}</div>
                  </div>
                  <button className="btn btn-danger" onClick={() => openEdit(ev)}>Edit</button>
                  <button className="btn btn-danger" onClick={() => removeEvent(ev)}>✕</button>
                </div>
              ))}
            </div>
          )}
        </aside>
      </div>

      {/* ADD MODAL */}
      {modal === "add" && (
        <div className="modal-overlay" onClick={() => setModal(null)}>
          <div className="modal" onClick={e => e.stopPropagation()}>
            <button className="modal-close" onClick={() => setModal(null)}>×</button>
            <h3>Add New Event</h3>
            <div className="form-row">
              <label>Event Title</label>
              <input value={form.title} onChange={e => setForm(f => ({ ...f, title: e.target.value }))}
                placeholder="e.g. Biology Quiz" autoFocus
                onKeyDown={e => e.key === "Enter" && saveAdd()} />
            </div>
            <div className="form-row">
              <label>Date (YYYY-MM-DD)</label>
              <input type="date" value={form.date} onChange={e => setForm(f => ({ ...f, date: e.target.value }))} />
            </div>
            <div className="modal-actions">
              <button className="btn btn-danger" onClick={() => setModal(null)}>Cancel</button>
              <button className="btn btn-primary" onClick={saveAdd}>Add Event</button>
            </div>
          </div>
        </div>
      )}

      {/* EDIT MODAL */}
      {modal === "edit" && (
        <div className="modal-overlay" onClick={() => setModal(null)}>
          <div className="modal" onClick={e => e.stopPropagation()}>
            <button className="modal-close" onClick={() => setModal(null)}>×</button>
            <h3>Edit Event</h3>
            <div className="form-row">
              <label>Event Title</label>
              <input value={form.title} onChange={e => setForm(f => ({ ...f, title: e.target.value }))}
                autoFocus onKeyDown={e => e.key === "Enter" && saveEdit()} />
            </div>
            <div className="form-row">
              <label>Date (YYYY-MM-DD)</label>
              <input type="date" value={form.date} onChange={e => setForm(f => ({ ...f, date: e.target.value }))} />
            </div>
            <div className="modal-actions">
              <button className="btn btn-danger" style={{ marginRight: "auto" }}
                onClick={() => { removeEvent(editTarget); setModal(null); }}>
                Delete
              </button>
              <button className="btn btn-danger" onClick={() => setModal(null)}>Cancel</button>
              <button className="btn btn-primary" onClick={saveEdit}>Save Changes</button>
            </div>
          </div>
        </div>
      )}

      {toast && <div className="toast">{toast}</div>}
    </>
  );
}
