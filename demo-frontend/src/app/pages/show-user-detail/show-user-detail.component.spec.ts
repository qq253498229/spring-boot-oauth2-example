import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowUserDetailComponent } from './show-user-detail.component';

describe('ShowUserDetailComponent', () => {
  let component: ShowUserDetailComponent;
  let fixture: ComponentFixture<ShowUserDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowUserDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowUserDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
