import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentAndRecreationistReservationListComponent } from './student-and-recreationist-reservation-list.component';

describe('StudentAndRecreationistReservationListComponent', () => {
  let component: StudentAndRecreationistReservationListComponent;
  let fixture: ComponentFixture<StudentAndRecreationistReservationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentAndRecreationistReservationListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentAndRecreationistReservationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
